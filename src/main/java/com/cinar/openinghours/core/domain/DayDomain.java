package com.cinar.openinghours.core.domain;

import com.cinar.openinghours.base.domain.Domain;
import com.cinar.openinghours.core.domain.enums.Types;
import com.cinar.openinghours.core.domain.enums.WeekDays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DayDomain extends Domain {

  private WeekDays workday;
  private List<HourDomain> hours;

  public void validate() {
    this.hours.forEach(HourDomain::validate);
  }

  public HourDomain getFirstClosedHour() {
    return this.hours
        .stream()
        .filter(h -> h.getType() == Types.CLOSED)
        .min(Comparator.comparing(HourDomain::getValue))
        .orElse(null);
  }

  public List<HourDomain> getUnusedHours(Map<Integer, HourDomain> usedHoursMap) {
    return this.hours
        .stream()
        .filter((h) -> usedHoursMap.get(Objects.hash(workday.getOrder(), h.hashCode())) == null)
        .collect(
            Collectors.toList());
  }

}
