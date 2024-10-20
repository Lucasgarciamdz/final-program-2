package ar.edu.um.pcbuilder.dtos.warehouse.requests;

public record Authenticate(String username, String password, boolean rememberMe) {

}
