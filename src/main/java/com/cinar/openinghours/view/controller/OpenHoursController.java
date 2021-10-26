package com.cinar.openinghours.view.controller;

import com.cinar.openinghours.core.usecase.GetHumanReadableHoursUseCase;
import com.cinar.openinghours.core.usecase.io.GetHumanReadableUseCaseInput;
import com.cinar.openinghours.core.usecase.io.GetHumanReadableUseCaseOutput;
import com.cinar.openinghours.view.dto.WeekDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hours")
@AllArgsConstructor
public class OpenHoursController {

  private final GetHumanReadableHoursUseCase getHumanReadableHoursUseCase;

  @PostMapping("/readable")
  public ResponseEntity<String> getReadableHours(@RequestBody WeekDto weekDto) {
    final GetHumanReadableUseCaseOutput execute = getHumanReadableHoursUseCase.execute(
        new GetHumanReadableUseCaseInput(weekDto.toWeekScheduleDomain()));
    return ResponseEntity.ok(execute.getReadableHours());
  }
}
