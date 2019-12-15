package pl.kubehe.cinema.domain.booking.usecase;

import io.vavr.Tuple;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.kubehe.cinema.domain.booking.dto.ScreeningRequest;
import pl.kubehe.cinema.domain.booking.dto.ScreeningResponse;
import pl.kubehe.cinema.infrastructure.model.ScreeningEntity;
import pl.kubehe.cinema.infrastructure.repository.ScreeningRepository;

import javax.validation.Valid;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Validated
@RequiredArgsConstructor
@Service
public class ScreeningQuery {

  private final ScreeningRepository screeningRepository;

  public Optional<ScreeningResponse> logic(@Valid final ScreeningRequest request) {
    return screeningRepository.findById(request.getScreeningId())
      .map(entity ->
        ScreeningResponse.builder()
          .availableSeats(calculateAvailableSeats(entity))
          .roomId(entity.getRoom().getId())
          .build()
      );
  }

  private Set<ScreeningResponse.Seat> calculateAvailableSeats(final ScreeningEntity screeningEntity) {
    var soldTickets = screeningEntity.getTickets();
    var columnsNumber = screeningEntity.getRoom().getColumnsNumber();
    var rowNumber = screeningEntity.getRoom().getRowsNumber();

    var reservedSeats = soldTickets.stream().map(ticket ->
      Tuple.of(ticket.getSeatRow(), ticket.getSeatColumn())
    )
      .collect(Collectors.toList());

    var allSeatsInRoom = Stream.ofAll(LongStream.range(0, rowNumber).mapToObj(column ->
      LongStream.range(0, columnsNumber).mapToObj(row -> Tuple.of(row, column))
    ).flatMap(Function.identity()).filter(Predicate.not(reservedSeats::contains))
      .map(tuple -> ScreeningResponse.Seat.builder().seatRow(tuple._1).seatColumn(tuple._2).build())).toSet();


    return allSeatsInRoom;
  }
}
