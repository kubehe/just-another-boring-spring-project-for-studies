package pl.kubehe.cinema.domain.booking.dto;

import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class TicketTypesResponse {

  Set<TicketType> ticketTypes;

  @Builder
  @Value
  public static class TicketType {
    Long id;
    String name;
    BigDecimal price;
  }
}
