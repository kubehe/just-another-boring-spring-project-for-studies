package pl.kubehe.cinema.domain.booking.dto;

import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;


@Builder
@Value
public class ScreeningResponse {

  Long roomId;
  String roomName;
  Set<Seat> availableSeats;

  @Builder
  @Value
  public static class Seat {
    Long seatRow;
    Long seatColumn;
  }
}

