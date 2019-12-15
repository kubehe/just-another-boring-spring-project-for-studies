package pl.kubehe.cinema.domain.booking.usecase;

import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.kubehe.cinema.domain.booking.dto.AvailableMoviesRequest;
import pl.kubehe.cinema.domain.booking.dto.AvailableMoviesResponse;
import pl.kubehe.cinema.infrastructure.model.MovieEntity;
import pl.kubehe.cinema.infrastructure.model.ScreeningEntity;
import pl.kubehe.cinema.infrastructure.repository.MovieRepository;
import pl.kubehe.cinema.infrastructure.service.TimeProvider;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Validated
@Service
@RequiredArgsConstructor
public class AvailableMoviesQuery {

  private final MovieRepository movieRepository;

  private final TimeProvider timeProvider;

  public AvailableMoviesResponse logic(@Valid final AvailableMoviesRequest request) {
    var movies = movieRepository.findAllByScreeningTime(timeProvider.getCurrentTime(), request.getStartTime(), request.getEndTime());
    return AvailableMoviesResponse.builder()
      .movies(List.ofAll(movies.stream()
        .map(this::map)
        .collect(Collectors.toList()))
      ).build();
  }

  private AvailableMoviesResponse.Movie map(final MovieEntity movie) {
    return AvailableMoviesResponse.Movie.builder()
      .id(movie.getId())
      .title(movie.getTitle())
      .screenings(List.ofAll(movie.getScreenings().stream()
        .map(this::map)
        .collect(Collectors.toList()))
      ).build();
  }

  private AvailableMoviesResponse.Movie.Screening map(final ScreeningEntity screening) {
    return AvailableMoviesResponse.Movie.Screening.builder()
      .id(screening.getId())
      .startTime(screening.getStartTime())
      .build();
  }
}
