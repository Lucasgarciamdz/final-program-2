package ar.edu.um.pcbuilder.utils;

import ar.edu.um.pcbuilder.exceptions.ErrorDetails;
import ar.edu.um.pcbuilder.exceptions.JwtException;
import ar.edu.um.pcbuilder.properties.JwtPpties;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtils {

  private final String privateKeyString;
  private final String publicKeyString;
  private final long expirationTime;
  private ECPrivateKey privateKey;
  private ECPublicKey publicKey;
  
  public JwtUtils(JwtPpties jwtPpties) {
    this.privateKeyString = jwtPpties.privateKey();
    this.publicKeyString = jwtPpties.publicKey();
    this.expirationTime = Long.parseLong(jwtPpties.expirationTime());
  }

  public String generateToken(String email) throws JwtException {
    try {
      log.info("Generating token for email: {}", email);
      JWSSigner signer = new ECDSASigner(privateKey);
      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
          .subject(email)
          .issueTime(new Date())
          .expirationTime(new Date(System.currentTimeMillis() + expirationTime))
          .build();

      SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.ES256).build(),
          claimsSet);
      signedJWT.sign(signer);

      String token = signedJWT.serialize();
      log.debug("Token generated: {}", token);
      return token;
    } catch (Exception e) {
      log.error("Error generating JWT token", e);
      throw new JwtException(ErrorDetails.JWT_GENERATION_ERROR.getMessage(),
          "Error generating JWT token");
    }
  }

  public String getUsernameFromToken(String token) throws JwtException {
    try {
      log.info("Extracting email from token");
      SignedJWT signedJWT = SignedJWT.parse(token);
      String email = signedJWT.getJWTClaimsSet().getSubject();
      log.debug("Username extracted: {}", email);
      return email;
    } catch (ParseException e) {
      log.error("Error parsing JWT token", e);
      throw new JwtException(ErrorDetails.JWT_PARSING_ERROR.getMessage(),
          "Error parsing JWT token");
    }
  }

  @PostConstruct
  public void init() throws JwtException {
    try {
      this.privateKey = (ECPrivateKey) getPrivateKey();
      this.publicKey = (ECPublicKey) getPublicKey();
    } catch (Exception e) {
      throw new JwtException(ErrorDetails.JWT_KEY_INITIALIZATION_ERROR.getMessage(),
          "Error initializing JWT keys");
    }
  }

  private PrivateKey getPrivateKey() throws Exception {
    byte[] keyBytes = Base64.getDecoder().decode(privateKeyString);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("EC");
    return keyFactory.generatePrivate(spec);
  }

  private PublicKey getPublicKey() throws Exception {
    byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("EC");
    return keyFactory.generatePublic(spec);
  }

  public boolean validateToken(String token) throws JwtException {
    try {
      log.info("Validating token");
      SignedJWT signedJWT = SignedJWT.parse(token);
      JWSVerifier verifier = new ECDSAVerifier(publicKey);
      boolean isValid = signedJWT.verify(verifier) && !isTokenExpired(signedJWT);
      log.debug("Token validation result: {}", isValid);
      return isValid;
    } catch (Exception e) {
      log.error("Error validating JWT token", e);
      throw new JwtException(ErrorDetails.JWT_VALIDATION_ERROR.getMessage(),
          "Error validating JWT token");
    }
  }

  private boolean isTokenExpired(SignedJWT token) throws JwtException {
    try {
      Date tokenExpirationTime = token.getJWTClaimsSet().getExpirationTime();
      boolean isExpired = tokenExpirationTime.before(new Date());
      log.debug("Token expiration result: {}", isExpired);
      return isExpired;
    } catch (ParseException e) {
      log.error("Error parsing JWT token expiration", e);
      throw new JwtException(ErrorDetails.JWT_PARSING_ERROR.getMessage(),
          "Error parsing JWT token expiration");
    }
  }
}