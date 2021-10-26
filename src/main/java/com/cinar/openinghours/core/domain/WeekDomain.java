package com.cinar.openinghours.core.domain;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.cinar.openinghours.base.domain.Domain;
import com.cinar.openinghours.base.exception.ValidationException;
import com.cinar.openinghours.core.domain.enums.Types;
import com.cinar.openinghours.core.domain.enums.WeekDays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WeekDomain extends Domain {

  private List<DayDomain> days;

  public void validate() {
    this.days.forEach(DayDomain::validate);
  }

  public void addDay(DayDomain daySchedule) {
    if (isEmpty(this.days)) {
      this.days = new ArrayList<>();
    }

    this.days.add(daySchedule);
  }


  public String getReadable() {
    Map<Integer, HourDomain> usedHours = new HashMap<>();
    StringBuilder builder = new StringBuilder();

    final Map<Integer, DayDomain> orderAndDayScheduleMap = this.days
        .stream()
        .collect(Collectors.toMap(dayDomain -> dayDomain.getWorkday().getOrder(),
            Function.identity()));

    for (DayDomain day : this.days) {
      final List<HourDomain> unusedHours = day.getUnusedHours(usedHours);
      builder.append(day.getWorkday().getValue());
      builder.append(": ");
      if (isEmpty(unusedHours)) {
        builder.append("Closed");
      } else {
        boolean nextDaySearchRequired = unusedHours.size() % 2 == 1;

        for (int i = 0; i < unusedHours.size(); i++) {
          final HourDomain hourDomain = unusedHours.get(i);
          if (i % 2 == 0 && i > 0 && hourDomain.getType() == Types.OPEN) {
            builder.append(" , ");
          }

          builder.append(hourDomain.getReadableHour());

          if (hourDomain.getType() == Types.OPEN) {
            builder.append(" - ");
          }
        }

        if (nextDaySearchRequired) {
          final int order = WeekDays.getNextDayFrom(day.getWorkday().getOrder())
              .getOrder();

          final DayDomain dayDomain = orderAndDayScheduleMap.get(order);
          final HourDomain firstClosedHour = Optional.ofNullable(dayDomain.getFirstClosedHour())
              .orElseThrow(() -> new ValidationException("Next closing hour cannot be found!"));

          builder.append(firstClosedHour.getReadableHour());

          usedHours.put(Objects.hash(order, firstClosedHour.hashCode()), firstClosedHour);
        }
      }

      builder.append('\n');
    }
    return builder.toString();
  }
}
