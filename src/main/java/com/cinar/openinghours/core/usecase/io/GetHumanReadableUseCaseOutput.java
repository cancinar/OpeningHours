package com.cinar.openinghours.core.usecase.io;

import com.cinar.openinghours.base.usecase.io.Output;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetHumanReadableUseCaseOutput implements Output {

  private String readableHours;
}
