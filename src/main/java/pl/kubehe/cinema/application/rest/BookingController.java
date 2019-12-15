package pl.kubehe.cinema.application.rest;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.kubehe.cinema.domain.booking.dto.*;
import pl.kubehe.cinema.domain.booking.usecase.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;


@AllArgsConstructor
@RestController
@RequestMapping(path = "/book")
public class BookingController {

  private AvailableMoviesQuery availableMoviesQuery;

  private ScreeningQuery screeningQuery;

  private ReservationCommand reservationCommand;

  private ReservationDetailsQuery reservationDetailsQuery;

  private TicketTypesQuery ticketTypesQuery;

  @GetMapping(path = "/movie")
  public ResponseEntity<AvailableMoviesResponse> getAvailableMoviesByDateTime(
    @RequestParam("startTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      LocalDateTime startTime,
    @RequestParam("endTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      LocalDateTime endTime) {
    var request = AvailableMoviesRequest.builder().startTime(startTime).endTime(endTime).build();

    return ok(availableMoviesQuery.logic(request));
  }

  @GetMapping(path = "/screening/{id}")
  public ResponseEntity<Optional<ScreeningResponse>> getScreeningInformation(@PathVariable Long id) {
    var request = ScreeningRequest.builder().screeningId(id).build();

    return ok(screeningQuery.logic(request));
  }

  @GetMapping(path = "/ticket/type")
  public ResponseEntity<TicketTypesResponse> getTicketTypes() {
    return ok(ticketTypesQuery.logic());
  }

  @PostMapping
  public ResponseEntity<?> postReservation(@RequestBody ReservationRequest request, HttpServletRequest servletRequest) {
    var uriPath = servletRequest.getRequestURI();

    // todo : change to configurable stuff
    var uri = UriComponentsBuilder.newInstance()
      .path(uriPath + "/{uuid}")
      .buildAndExpand(reservationCommand.logic(request).getReservationUuid())
      .toUri();

    return ResponseEntity.created(uri).build();
  }

  @GetMapping(path = "/{uuid}")
  public ResponseEntity<ReservationDetailsResponse> getReservationDetails(@PathVariable UUID uuid) {
    var request = ReservationDetailsRequest.builder()
      .reservationUuid(uuid)
      .build();

    return ok(reservationDetailsQuery.logic(request));
  }

}
