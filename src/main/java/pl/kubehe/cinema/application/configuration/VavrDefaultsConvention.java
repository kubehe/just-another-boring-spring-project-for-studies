package pl.kubehe.cinema.application.configuration;

import com.fasterxml.classmate.TypeResolver;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.schema.WildcardType;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Component
@RequiredArgsConstructor
public class VavrDefaultsConvention implements AlternateTypeRuleConvention {

  private final TypeResolver typeResolver;

  @Override
  public List<AlternateTypeRule> rules() {
    var rules = new ArrayList<AlternateTypeRule>();
    rules.add(
      newRule(
        typeResolver.resolve(io.vavr.collection.List.class, WildcardType.class),
        typeResolver.resolve(List.class, WildcardType.class)));
    rules.add(
      newRule(
        typeResolver.resolve(Set.class, WildcardType.class),
        typeResolver.resolve(java.util.Set.class, WildcardType.class)));
    rules.add(
      newRule(
        typeResolver.resolve(Map.class, WildcardType.class, WildcardType.class),
        typeResolver.resolve(java.util.Map.class, WildcardType.class, WildcardType.class)));
    return rules;
  }

  @Override
  public int getOrder() {
    return HIGHEST_PRECEDENCE;
  }
}
