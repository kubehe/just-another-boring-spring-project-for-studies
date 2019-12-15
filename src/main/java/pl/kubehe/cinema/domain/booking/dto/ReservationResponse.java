package pl.kubehe.cinema.domain.booking.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class ReservationResponse {

  UUID reservationUuid;
}
