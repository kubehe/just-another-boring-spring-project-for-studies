package pl.kubehe.cinema.domain.booking.usecase;

import io.vavr.Tuple;
import io.vavr.collection.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.kubehe.cinema.domain.booking.dto.ReservationRequest;
import pl.kubehe.cinema.domain.booking.dto.ReservationResponse;
import pl.kubehe.cinema.infrastructure.model.ReservationEntity;
import pl.kubehe.cinema.infrastructure.model.TicketEntity;
import pl.kubehe.cinema.infrastructure.repository.ReservationRepository;
import pl.kubehe.cinema.infrastructure.repository.ScreeningRepository;
import pl.kubehe.cinema.infrastructure.repository.TicketRepository;
import pl.kubehe.cinema.infrastructure.repository.TicketTypeRepository;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@Service
public class ReservationCommand {

  private final ReservationRepository reservationRepository;
  private final ScreeningRepository screeningRepository;
  private final TicketTypeRepository ticketTypeRepository;
  private final TicketRepository ticketRepository;


  public ReservationResponse logic(@Valid ReservationRequest request) {
    var screening = screeningRepository.findById(request.getScreeningId())
      .orElseThrow(() -> new RuntimeException("screening not found"));

    var ticketsToBuy = request.getSeats().toStream()
      .map(seat -> TicketEntity.builder()
        .ticketType(ticketTypeRepository.findById(seat.getTicketTypeId()).orElseThrow(() -> new RuntimeException("Ticket type not found")))
        .seatColumn(seat.getSeatColumn())
        .seatRow(seat.getSeatRow())
        .build()).toSet();


    var soldTickets = Stream.ofAll(screening.getTickets().stream()).toSet();

    var ticketsItersection = ticketsToBuy.map(ticket ->
      Tuple.of(ticket.getSeatRow(), ticket.getSeatColumn()))
      .intersect(soldTickets
        .map(ticket -> Tuple.of(ticket.getSeatRow(), ticket.getSeatColumn())
        )
      );

    if (!ticketsItersection.isEmpty()) {
      throw new RuntimeException("some of tickets are already sold: " + ticketsItersection.toString());
    }

    var reservation = ReservationEntity.builder()
      .name(request.getName())
      .screening(screening)
      .surname(request.getSurname())
      .tickets(ticketsToBuy.toJavaSet())
      .uuid(UUID.randomUUID())
      .build();

    var savedReservation = reservationRepository.save(reservation);
    ticketsToBuy.forEach(ticket -> {
      ticket.setReservation(savedReservation);
      ticketRepository.save(ticket);
    });
    return ReservationResponse.builder()
      .reservationUuid(savedReservation.getUuid())
      .build();
  }
}
