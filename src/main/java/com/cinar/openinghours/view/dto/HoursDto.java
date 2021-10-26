package com.cinar.openinghours.view.dto;

import static org.springframework.util.StringUtils.capitalize;

import com.cinar.openinghours.core.domain.HourDomain;
import com.cinar.openinghours.core.domain.enums.Types;
import lombok.Data;

@Data
public class HoursDto {

  private String type;
  private Long value;

  public HourDomain toHourScheduleDomain() {
    return HourDomain.builder()
        .value(this.value)
        .type(Types.valueOfString(capitalize(this.type)))
        .build();
  }
}
