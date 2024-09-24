//package ar.edu.um.pcbuilder.utils;
//
//import com.nimbusds.jose.JOSEException;
//import com.nimbusds.jose.JWSAlgorithm;
//import com.nimbusds.jose.JWSHeader;
//import com.nimbusds.jose.JWSSigner;
//import com.nimbusds.jose.JWSVerifier;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jose.crypto.MACVerifier;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import java.text.ParseException;
//import java.util.Date;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class JwtUtils {
//
//  @Value("${jwt.secret}")
//  private String secret;
//
//  @Value("${jwt.expiration}")
//  private long expirationTime;
//
//  public String generateToken(String username) {
//    try {
//      log.info("Generating token for username: {}", username);
//      JWSSigner signer = new MACSigner(secret);
//      JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//          .subject(username)
//          .issueTime(new Date())
//          .expirationTime(new Date(new Date().getTime() + expirationTime))
//          .build();
//
//      SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
//      signedJWT.sign(signer);
//
//      log.debug("Token generated: {}", signedJWT.serialize());
//      return signedJWT.serialize();
//    } catch (JOSEException e) {
//      log.error("Error generating JWT token", e);
//      throw new RuntimeException("Error generating JWT token", e);
//    }
//  }
//
//  public String getUsernameFromToken(String token) {
//    try {
//      log.info("Extracting username from token");
//      SignedJWT signedJWT = SignedJWT.parse(token);
//      String username = signedJWT.getJWTClaimsSet().getSubject();
//      log.debug("Username extracted: {}", username);
//      return username;
//    } catch (ParseException e) {
//      log.error("Error parsing JWT token", e);
//      throw new RuntimeException("Error parsing JWT token", e);
//    }
//  }
//
//  public boolean validateToken(String token) {
//    try {
//      log.info("Validating token");
//      SignedJWT signedJWT = SignedJWT.parse(token);
//      JWSVerifier verifier = new MACVerifier(secret);
//      boolean isValid = signedJWT.verify(verifier) && !isTokenExpired(token);
//      log.debug("Token validation result: {}", isValid);
//      return isValid;
//    } catch (JOSEException | ParseException e) {
//      log.error("Error validating JWT token", e);
//      throw new RuntimeException("Error validating JWT token", e);
//    }
//  }
//
//  public boolean isTokenExpired(String token) {
//    try {
//      log.info("Checking if token is expired");
//      SignedJWT signedJWT = SignedJWT.parse(token);
//      Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
//      boolean isExpired = expirationTime.before(new Date());
//      log.debug("Token expiration result: {}", isExpired);
//      return isExpired;
//    } catch (ParseException e) {
//      log.error("Error parsing JWT token", e);
//      throw new RuntimeException("Error parsing JWT token", e);
//    }
//  }
//}