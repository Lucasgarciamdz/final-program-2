package ar.edu.um.pcbuilder.exceptions;

import lombok.Getter;

@Getter
public class JwtException extends Exception {
  private final String details;

  public JwtException(String message, String details) {
    super(message);
    this.details = details;
  }

}