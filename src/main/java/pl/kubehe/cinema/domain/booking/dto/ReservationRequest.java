package pl.kubehe.cinema.domain.booking.dto;

import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Builder
@Value
public class ReservationRequest {

  @NotNull(message = "{ReservationRequest.name.NotNull}")
  @Size(min = 3, max = 255, message = "{ReservationRequest.name.Size}")
  String name;

  @NotNull(message = "{ReservationRequest.surname.NotNull}")
  @Size(min = 3, max = 255, message = "{ReservationRequest.surname.Size}")
  String surname;

  @NotNull(message = "{ReservationRequest.screeningId.NotNull}")
  Long screeningId;

  @Valid
  @NotNull(message = "{ReservationRequest.seats.NotNull}")
  Set<Seat> seats;

  @Builder
  @Value
  public static class Seat {

    @NotNull(message = "{ReservationRequest.Seat.ticketTypeId.NotNull}")
    Long ticketTypeId;

    @NotNull(message = "{ReservationRequest.Seat.seatRow.NotNull}")
    Long seatRow;

    @NotNull(message = "{ReservationRequest.Seat.seatColumn.NotNull}")
    Long seatColumn;

  }
}
