package pl.kubehe.cinema.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "pl.kubehe.cinema")
@EnableJpaRepositories(basePackages = "pl.kubehe.cinema.infrastructure.repository")
@EntityScan(basePackages = "pl.kubehe.cinema.infrastructure.model")
public class CinemaApplication {

  public static void main(String[] args) {
    SpringApplication.run(CinemaApplication.class, args);
  }

}
