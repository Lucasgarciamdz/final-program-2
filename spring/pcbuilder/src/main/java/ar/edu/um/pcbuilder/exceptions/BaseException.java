package ar.edu.um.pcbuilder.exceptions;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends Exception {

  private final String errorCode;
  private final LocalDateTime timestamp;
  private final String customMessage;

  public BaseException(String message, String errorCode, String customMessage) {
    super(message);
    this.errorCode = errorCode;
    this.timestamp = LocalDateTime.now();
    this.customMessage = customMessage;
  }

}