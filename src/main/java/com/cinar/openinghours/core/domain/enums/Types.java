package com.cinar.openinghours.core.domain.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Types {

  CLOSED("Close"),
  OPEN("Open");

  private final String value;

  private static final Map<String, Types> TYPES = new HashMap<>();

  static {
    for (Types e : values()) {
      TYPES.put(e.value, e);
    }
  }

  public static Types valueOfString(String label) {
    return TYPES.get(label);
  }

}
