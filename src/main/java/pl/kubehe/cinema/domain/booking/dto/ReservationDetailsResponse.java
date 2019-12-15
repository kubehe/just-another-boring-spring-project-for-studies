package pl.kubehe.cinema.domain.booking.dto;

import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Value
public class ReservationDetailsResponse {

  BigDecimal totalAmountToPay;
  String movieTitle;
  String userName;
  String userSurname;
  LocalDateTime movieStartTime;
  String roomName;


  Set<Seat> seats;

  @Builder
  @Value
  public static class Seat {
    Long seatRow;
    Long seatColumn;
  }
}
