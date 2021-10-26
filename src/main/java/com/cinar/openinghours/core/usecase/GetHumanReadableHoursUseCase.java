package com.cinar.openinghours.core.usecase;

import com.cinar.openinghours.base.usecase.UseCase;
import com.cinar.openinghours.core.usecase.io.GetHumanReadableUseCaseInput;
import com.cinar.openinghours.core.usecase.io.GetHumanReadableUseCaseOutput;
import org.springframework.stereotype.Service;

@Service
public class GetHumanReadableHoursUseCase implements
    UseCase<GetHumanReadableUseCaseInput, GetHumanReadableUseCaseOutput> {

  @Override
  public void validate(GetHumanReadableUseCaseInput input) {
    input.getWeekSchedule().validate();
  }

  @Override
  public GetHumanReadableUseCaseOutput apply(GetHumanReadableUseCaseInput input) {
    return new GetHumanReadableUseCaseOutput(input.getWeekSchedule().getReadable());
  }
}
