package ar.edu.um.pcbuilder.exceptions;

import ar.edu.um.pcbuilder.dtos.responses.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest request) {
    return buildExceptionResponse(ErrorDetails.INTERNAL_SERVER_ERROR, request);
  }

  private ResponseEntity<ExceptionResponse> buildExceptionResponse(ErrorDetails errorCode,
      WebRequest request) {
    ExceptionResponse errorResponse = new ExceptionResponse(
        errorCode.getStatus().value(), errorCode.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorResponse, errorCode.getStatus());
  }
}
