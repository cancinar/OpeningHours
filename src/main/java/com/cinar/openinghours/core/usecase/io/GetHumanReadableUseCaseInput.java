package com.cinar.openinghours.core.usecase.io;

import com.cinar.openinghours.base.usecase.io.Input;
import com.cinar.openinghours.core.domain.WeekDomain;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetHumanReadableUseCaseInput implements Input {

  private WeekDomain weekSchedule;
}
