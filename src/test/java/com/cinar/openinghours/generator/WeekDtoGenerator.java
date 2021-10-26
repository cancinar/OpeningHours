package com.cinar.openinghours.generator;

import com.cinar.openinghours.view.dto.WeekDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WeekDtoGenerator {

  public WeekDto generate() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<WeekDto> typeReference = new TypeReference<WeekDto>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream(
        "/json/test-data.json");

    return mapper.readValue(inputStream, typeReference);
  }
}
