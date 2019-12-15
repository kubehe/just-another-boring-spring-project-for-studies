package pl.kubehe.cinema.domain.booking.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Builder
@Value
public class ScreeningRequest {
  @NotNull(message = "{ScreeningRequest.screeningId.NotNull}")
  Long screeningId;
}
