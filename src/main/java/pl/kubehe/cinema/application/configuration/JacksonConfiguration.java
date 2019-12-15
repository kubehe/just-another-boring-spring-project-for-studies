package pl.kubehe.cinema.application.configuration;

import com.fasterxml.jackson.databind.Module;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

  // serialize/deserialize immutable collections in request and response dto
  @Bean
  public Module vavrModule() {
    return new VavrModule();
  }
}
