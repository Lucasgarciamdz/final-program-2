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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

  private final long expirationTime;
  private ECPrivateKey privateKey;
  private ECPublicKey publicKey;

  public JwtUtils(JwtPpties jwtPpties) {
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

      SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.ES256).build(), claimsSet);
      signedJWT.sign(signer);

      String token = signedJWT.serialize();
      log.debug("Token generated: {}", token);
      return token;
    } catch (Exception e) {
      log.error("Error generating JWT token", e);
      throw new JwtException(ErrorDetails.JWT_GENERATION_ERROR.getMessage(), "Error generating JWT token");
    }
  }

  public String getUsernameFromToken(String token) throws JwtException {
    try {
      log.info("Extracting email from token");
      SignedJWT signedJWT = SignedJWT.parse(token);
      String email = signedJWT.getJWTClaimsSet().getSubject();
      log.debug("Username extracted: {}", email);
      return email;
    } catch (Exception e) {
      log.error("Error parsing JWT token", e);
      throw new JwtException(ErrorDetails.JWT_PARSING_ERROR.getMessage(), "Error parsing JWT token");
    }
  }

  @PostConstruct
  public void init() throws JwtException {
    try {
      // Generate the EC key pair
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
      keyPairGenerator.initialize(256); // Specify the key size for ES256 (P-256 curve)
      KeyPair keyPair = keyPairGenerator.generateKeyPair();

      this.privateKey = (ECPrivateKey) keyPair.getPrivate();
      this.publicKey = (ECPublicKey) keyPair.getPublic();

      log.info("Successfully generated EC key pair");
    } catch (NoSuchAlgorithmException e) {
      log.error("Error generating EC key pair", e);
      throw new JwtException(ErrorDetails.JWT_KEY_INITIALIZATION_ERROR.getMessage(), "Error generating EC key pair");
    }
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
      throw new JwtException(ErrorDetails.JWT_VALIDATION_ERROR.getMessage(), "Error validating JWT token");
    }
  }

  private boolean isTokenExpired(SignedJWT token) throws JwtException {
    try {
      Date tokenExpirationTime = token.getJWTClaimsSet().getExpirationTime();
      boolean isExpired = tokenExpirationTime.before(new Date());
      log.debug("Token expiration result: {}", isExpired);
      return isExpired;
    } catch (Exception e) {
      log.error("Error parsing JWT token expiration", e);
      throw new JwtException(ErrorDetails.JWT_PARSING_ERROR.getMessage(), "Error parsing JWT token expiration");
    }
  }
}
