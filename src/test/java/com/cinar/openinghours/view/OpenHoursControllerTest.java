package com.cinar.openinghours.view;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cinar.openinghours.core.usecase.GetHumanReadableHoursUseCase;
import com.cinar.openinghours.core.usecase.io.GetHumanReadableUseCaseOutput;
import com.cinar.openinghours.view.controller.OpenHoursController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OpenHoursController.class)
public class OpenHoursControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private GetHumanReadableHoursUseCase getHumanReadableHoursUseCase;

  @Test
  void getReadableHours_thenReturns200() throws Exception {
    final String s = readFile("classpath:json/test-data.json");
    when(getHumanReadableHoursUseCase.execute(any())).thenReturn(
        new GetHumanReadableUseCaseOutput(""));
    mockMvc.perform(post("/hours/readable")
            .content(s)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  public String readFile(String path) {
    try {
      File file = ResourceUtils.getFile(path);
      return new String(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
