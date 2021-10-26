package com.cinar.openinghours.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cinar.openinghours.core.domain.WeekDomain;
import com.cinar.openinghours.core.usecase.io.GetHumanReadableUseCaseInput;
import com.cinar.openinghours.core.usecase.io.GetHumanReadableUseCaseOutput;
import com.cinar.openinghours.generator.WeekDtoGenerator;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetHumanReadableHoursUseCaseTest {

  private GetHumanReadableHoursUseCase getHumanReadableHoursUseCase;

  @BeforeEach
  public void init() {
    this.getHumanReadableHoursUseCase = new GetHumanReadableHoursUseCase();
  }

  @Test
  public void execute_thenReturn() throws IOException {
    final WeekDomain weekDomain = WeekDtoGenerator.generate()
        .toWeekScheduleDomain();

    final GetHumanReadableUseCaseOutput execute = getHumanReadableHoursUseCase.execute(
        new GetHumanReadableUseCaseInput(weekDomain));

    String expected = "Monday: Closed\n"
        + "Tuesday: 10 AM - 6 PM\n"
        + "Wednesday: Closed\n"
        + "Thursday: 10:30 AM - 6 PM\n"
        + "Friday: 10 AM - 1 AM\n"
        + "Saturday: 10 AM - 1 AM\n"
        + "Sunday: 12 PM - 9 PM\n";

    assertEquals(expected, execute.getReadableHours());
  }
}
