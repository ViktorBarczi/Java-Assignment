package com.projekt.app.exceptions;

/**
 * Exception thrown when a bad request is made to the server.
 * This could be due to invalid input data or missing required fields.
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
