package ar.edu.um.pcbuilder.exceptions;

public class JwtException extends Exception {
  private final String details;

  public JwtException(String message, String details) {
    super(message);
    this.details = details;
  }

  public String getDetails() {
    return details;
  }
}