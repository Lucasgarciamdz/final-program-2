package ar.edu.um.pcbuilder.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wharehouse")
public record WharehousePpties(String jwt, String baseUrl) {

}
