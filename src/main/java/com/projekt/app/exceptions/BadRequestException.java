package com.projekt.app.exceptions;

/**
 * Exception thrown when a bad request is made to the server.
 * This could be due to invalid input data or missing required fields.
 */
public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

}
