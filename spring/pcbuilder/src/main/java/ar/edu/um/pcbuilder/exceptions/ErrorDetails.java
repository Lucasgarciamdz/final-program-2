package ar.edu.um.pcbuilder.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorDetails {
  RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "The requested resource was not found"),
  INVALID_REQUEST(HttpStatus.BAD_REQUEST, "The request is invalid"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "You are not authorized to perform this action"),
  JWT_GENERATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating JWT token"),
  JWT_PARSING_ERROR(HttpStatus.BAD_REQUEST, "Error parsing JWT token"),
  JWT_VALIDATION_ERROR(HttpStatus.UNAUTHORIZED, "Error validating JWT token"), 
  JWT_KEY_INITIALIZATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Error initializing JWT keys");

  private final HttpStatus status;
  private final String message;

  ErrorDetails(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}