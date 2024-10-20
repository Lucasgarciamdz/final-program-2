package ar.edu.um.pcbuilder.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "warehouse")
public record WarehousePpties(String jwt, String baseUrl) {

}
