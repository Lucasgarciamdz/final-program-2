package ar.edu.um.pcbuilder.exceptions;

import java.time.LocalDateTime;

public class UserException extends BaseException {

  public UserException(String message, String errorDetail, String customMessage) {
    super(message, errorDetail, customMessage);
  }
}