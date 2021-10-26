package com.cinar.openinghours.base.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {

  public static final String VALUE_NOT_EMPTY = "Value cannot be empty!";
  public static final String TYPE_CLOSED_OR_OPEN = "Type can be closed or open.";
  public static final String VALUE_NOT_LARGER_THAN = "Value cannot be larger than %s!";
}
