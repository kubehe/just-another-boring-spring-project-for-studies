package pl.kubehe.cinema.domain.booking.dto;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class AvailableMoviesResponse {
  List<Movie> movies;

  @Builder
  @Value
  public static class Movie {
    Long id;
    String title;
    List<Screening> screenings;

    @Builder
    @Value
    public static class Screening {
      Long id;
      LocalDateTime startTime;
    }
  }
}
