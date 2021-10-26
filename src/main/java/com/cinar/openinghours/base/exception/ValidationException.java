package com.cinar.openinghours.base.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseException {

  public ValidationException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
