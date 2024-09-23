package ar.edu.um.pcbuilder.exceptions;

import org.springframework.http.HttpStatus;


public enum ErrorDetails {
  RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "The requested resource was not found"),
  INVALID_REQUEST(HttpStatus.BAD_REQUEST, "The request is invalid"),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred"),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "You are not authorized to perform this action");

  private final HttpStatus status;
  private final String message;

  ErrorDetails(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
