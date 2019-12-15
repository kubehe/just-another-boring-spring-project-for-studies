package pl.kubehe.cinema.domain.booking.usecase;

import io.vavr.collection.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.kubehe.cinema.domain.booking.dto.ReservationDetailsRequest;
import pl.kubehe.cinema.domain.booking.dto.ReservationDetailsResponse;
import pl.kubehe.cinema.infrastructure.repository.ReservationRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.math.BigDecimal;

@Validated
@RequiredArgsConstructor
@Service
public class ReservationDetailsQuery {

  private final ReservationRepository reservationRepository;


  public ReservationDetailsResponse logic(@Valid final ReservationDetailsRequest request) {
    var reservation = reservationRepository.findByUuid(request.getReservationUuid()).orElseThrow(() -> new EntityNotFoundException("Reservation with provided code does not exist"));
    return ReservationDetailsResponse.builder()
      .totalAmountToPay(reservation.getTickets().stream()
        .map(ticket -> ticket.getTicketType().getPrice())
        .reduce(BigDecimal.ZERO, BigDecimal::add))
      .movieStartTime(reservation.getScreening().getStartTime())
      .movieTitle(reservation.getScreening().getMovie().getTitle())
      .roomName(reservation.getScreening().getRoom().getName())
      .userName(reservation.getName())
      .userSurname(reservation.getSurname())
      .seats(Stream.ofAll(reservation.getTickets().stream())
        .map(ticket ->
          ReservationDetailsResponse.Seat.builder()
            .seatColumn(ticket.getSeatColumn())
            .seatRow(ticket.getSeatRow())
            .build())
        .toSet())
      .build();

  }
}
