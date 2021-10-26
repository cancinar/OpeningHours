package com.cinar.openinghours.core.domain.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeekDays {

  MONDAY("Monday", 0),
  TUESDAY("Tuesday", 1),
  WEDNESDAY("Wednesday", 2),
  THURSDAY("Thursday", 3),
  FRIDAY("Friday", 4),
  SATURDAY("Saturday", 5),
  SUNDAY("Sunday", 6);

  private static final Map<Integer, WeekDays> STATUSES = new HashMap<>();
  private final String value;
  private final int order;

  static {
    for (WeekDays e : values()) {
      STATUSES.put(e.order, e);
    }
  }

  public static WeekDays getNextDayFrom(int currentDay) {
    if (currentDay == 6) {
      return MONDAY;
    }

    return STATUSES.get(currentDay + 1);
  }
}
