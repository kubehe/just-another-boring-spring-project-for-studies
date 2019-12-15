package pl.kubehe.cinema.domain.booking.usecase;

import io.vavr.collection.Stream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kubehe.cinema.domain.booking.dto.TicketTypesResponse;
import pl.kubehe.cinema.infrastructure.model.TicketTypeEntity;
import pl.kubehe.cinema.infrastructure.repository.TicketTypeRepository;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class TicketTypesQuery {

  private final TicketTypeRepository ticketTypeRepository;


  public TicketTypesResponse logic() {

    final var ticketEntities = ticketTypeRepository.findAll();

    if (ticketEntities.isEmpty()) {
      throw new EntityNotFoundException("No ticket type available");
    }

    final var tickets = Stream.ofAll(ticketEntities.stream())
      .map(this::mapToDto)
      .toSet();

    return TicketTypesResponse.builder()
      .ticketTypes(tickets)
      .build();
  }

  private TicketTypesResponse.TicketType mapToDto(final TicketTypeEntity entity) {
    if (entity == null) return null;

    return TicketTypesResponse.TicketType.builder()
      .id(entity.getId())
      .name(entity.getName())
      .price(entity.getPrice())
      .build();
  }
}
