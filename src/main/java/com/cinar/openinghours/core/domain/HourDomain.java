package com.cinar.openinghours.core.domain;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

import com.cinar.openinghours.base.domain.Domain;
import com.cinar.openinghours.base.exception.ErrorMessage;
import com.cinar.openinghours.base.exception.ValidationException;
import com.cinar.openinghours.core.domain.enums.Types;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class HourDomain extends Domain {

  protected final static Long MAX_VALUE = 86399L;
  private final static String FORMAT_WITH_HOUR = "h:mm a";
  private final static String FORMAT_WITHOUT_HOUR = "h a";

  private Types type;
  private Long value;

  public void validate() {
    if (isEmpty(this.type)) {
      throw new ValidationException(ErrorMessage.TYPE_CLOSED_OR_OPEN);
    }
    if (isEmpty(this.value)) {
      throw new ValidationException(ErrorMessage.VALUE_NOT_EMPTY);
    }
    if (this.value > MAX_VALUE) {
      throw new ValidationException(format(ErrorMessage.VALUE_NOT_LARGER_THAN, MAX_VALUE));
    }
  }

  public String getReadableHour() {
    final LocalDateTime localDateTime = Instant.ofEpochSecond(this.value)
        .atZone(ZoneId.of("Etc/GMT-0"))
        .toLocalDateTime();

    if (localDateTime.getMinute() == 0) {
      return localDateTime
          .format(DateTimeFormatter.ofPattern(FORMAT_WITHOUT_HOUR, Locale.US));
    } else {
      return localDateTime
          .format(DateTimeFormatter.ofPattern(FORMAT_WITH_HOUR, Locale.US));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HourDomain that = (HourDomain) o;
    return type == that.type && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, value);
  }
}
