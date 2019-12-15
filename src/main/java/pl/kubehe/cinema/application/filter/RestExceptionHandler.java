package pl.kubehe.cinema.application.filter;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException exception) {
    final var constraints = exception.getConstraintViolations().stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError.builder()
      .message(List.ofAll(constraints))
      .build());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException exception) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.builder()
      .message(List.of(exception.getMessage()))
      .build());
  }

  @Builder
  @Value
  private static class ApiError {
    // internationalization could be added to handle messages in different languages
    List<String> message;
  }
}
