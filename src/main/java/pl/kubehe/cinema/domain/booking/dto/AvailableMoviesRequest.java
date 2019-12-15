package pl.kubehe.cinema.domain.booking.dto;

import lombok.Builder;
import lombok.Value;
import pl.kubehe.cinema.infrastructure.validation.LocalDateTimeRange;

import java.time.LocalDateTime;

@LocalDateTimeRange(startFieldName = "startTime", endFieldName = "endTime")
@Builder
@Value
public class AvailableMoviesRequest {
  LocalDateTime startTime;
  LocalDateTime endTime;
}
