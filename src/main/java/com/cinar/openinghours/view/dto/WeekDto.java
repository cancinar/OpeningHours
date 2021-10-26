package com.cinar.openinghours.view.dto;

import com.cinar.openinghours.core.domain.DayDomain;
import com.cinar.openinghours.core.domain.WeekDomain;
import com.cinar.openinghours.core.domain.enums.WeekDays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class WeekDto {

  private List<HoursDto> monday;
  private List<HoursDto> tuesday;
  private List<HoursDto> wednesday;
  private List<HoursDto> thursday;
  private List<HoursDto> friday;
  private List<HoursDto> saturday;
  private List<HoursDto> sunday;

  public WeekDomain toWeekScheduleDomain() {
    WeekDomain weekDomain = new WeekDomain();

    weekDomain.addDay(DayDomain.builder()
        .workday(WeekDays.MONDAY)
        .hours(
            this.monday
                .stream()
                .map(HoursDto::toHourScheduleDomain)
                .collect(Collectors.toList()))
        .build());
    weekDomain.addDay(DayDomain.builder()
        .workday(WeekDays.TUESDAY)
        .hours(
            this.tuesday
                .stream()
                .map(HoursDto::toHourScheduleDomain)
                .collect(Collectors.toList()))
        .build());

    weekDomain.addDay(DayDomain.builder()
        .workday(WeekDays.WEDNESDAY)
        .hours(
            this.thursday
                .stream()
                .map(HoursDto::toHourScheduleDomain)
                .collect(Collectors.toList()))
        .build());
    weekDomain.addDay(DayDomain.builder()
        .workday(WeekDays.THURSDAY)
        .hours(
            this.friday
                .stream()
                .map(HoursDto::toHourScheduleDomain)
                .collect(Collectors.toList()))
        .build());
    weekDomain.addDay(DayDomain.builder()
        .workday(WeekDays.FRIDAY)
        .hours(
            this.saturday
                .stream()
                .map(HoursDto::toHourScheduleDomain)
                .collect(Collectors.toList()))
        .build());
    weekDomain.addDay(DayDomain.builder()
        .workday(WeekDays.SATURDAY)
        .hours(
            this.saturday
                .stream()
                .map(HoursDto::toHourScheduleDomain)
                .collect(Collectors.toList()))
        .build());
    weekDomain.addDay(DayDomain.builder()
        .workday(WeekDays.SUNDAY)
        .hours(
            this.sunday
                .stream()
                .map(HoursDto::toHourScheduleDomain)
                .collect(Collectors.toList()))
        .build());

    return weekDomain;
  }
}
