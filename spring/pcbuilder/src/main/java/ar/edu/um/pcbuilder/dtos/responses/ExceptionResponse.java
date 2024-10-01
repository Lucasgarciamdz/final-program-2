package ar.edu.um.pcbuilder.dtos.responses;

public record ExceptionResponse(int statusCode, String message, String details) {

  @Override
  public String toString() {
    return "Status Code: " + statusCode + " | " +
           "Message: " + message + " | " +
           "Details: " + details;
  }
}