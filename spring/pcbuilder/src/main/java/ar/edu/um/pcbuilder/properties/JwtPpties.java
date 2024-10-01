package ar.edu.um.pcbuilder.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtPpties(String publicKey, String privateKey, String expirationTime) {
}
