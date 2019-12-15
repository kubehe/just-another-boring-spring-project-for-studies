package pl.kubehe.cinema.domain.booking.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Value
public class ReservationDetailsRequest {
  @NotNull(message = "{ReservationDetailsRequest.reservationUuid.NotNull}")
  UUID reservationUuid;
}
