package pl.kubehe.cinema.infrastructure.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.Optional.ofNullable;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = LocalDateTimeRangeValidator.class)
@Documented
public @interface LocalDateTimeRange {

  String startFieldName();

  String endFieldName();

  String message() default "{LocalDateTimeRange.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

class LocalDateTimeRangeValidator implements ConstraintValidator<LocalDateTimeRange, Object> {

  private String startFieldName;
  private String endFieldName;

  @Override
  public void initialize(LocalDateTimeRange constraintAnnotation) {
    this.startFieldName = constraintAnnotation.startFieldName();
    this.endFieldName = constraintAnnotation.endFieldName();

  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    var startFieldValue = ofNullable((LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(startFieldName));
    var endFieldValue = ofNullable((LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(endFieldName));

    return endFieldValue
      .flatMap(endTime -> startFieldValue
        .map(startTime -> startTime.compareTo(endTime)))
      .map(i -> i < 0)
      .orElse(false);
  }
}
