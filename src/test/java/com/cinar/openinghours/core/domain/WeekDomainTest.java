package com.cinar.openinghours.core.domain;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cinar.openinghours.base.exception.ErrorMessage;
import com.cinar.openinghours.base.exception.ValidationException;
import com.cinar.openinghours.generator.WeekDtoGenerator;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class WeekDomainTest {

  @Test
  public void validate_whenValueIsNull_thenThrowValidationException() throws IOException {
    final WeekDomain weekDomain = WeekDtoGenerator.generate()
        .toWeekScheduleDomain();

    weekDomain.getDays().get(1).getHours().get(0).setValue(null);

    final ValidationException validationException = assertThrows(ValidationException.class,
        weekDomain::validate);

    assertEquals(ErrorMessage.VALUE_NOT_EMPTY, validationException.getMessage());
  }

  @Test
  public void validate_whenTypeIsNull_thenThrowValidationException() throws IOException {
    final WeekDomain weekDomain = WeekDtoGenerator.generate()
        .toWeekScheduleDomain();

    weekDomain.getDays().get(1).getHours().get(0).setType(null);

    final ValidationException validationException = assertThrows(ValidationException.class,
        weekDomain::validate);

    assertEquals(ErrorMessage.TYPE_CLOSED_OR_OPEN, validationException.getMessage());
  }

  @Test
  public void validate_whenValueLargerThanMax_thenThrowValidationException() throws IOException {
    final WeekDomain weekDomain = WeekDtoGenerator.generate()
        .toWeekScheduleDomain();

    weekDomain.getDays().get(1).getHours().get(0)
        .setValue(HourDomain.MAX_VALUE + 1);

    final ValidationException validationException = assertThrows(ValidationException.class,
        weekDomain::validate);

    assertEquals(format(ErrorMessage.VALUE_NOT_LARGER_THAN, HourDomain.MAX_VALUE),
        validationException.getMessage());
  }
}
