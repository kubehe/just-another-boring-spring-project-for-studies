package pl.kubehe.cinema.application.configuration;

import io.vavr.jackson.datatype.VavrModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.configuration.ObjectMapperConfigured;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements ApplicationListener<ObjectMapperConfigured> {

  @Value("${swagger.api.title}")
  private String title;

  @Value("${swagger.api.description}")
  private String description;

  @Value("${swagger.api.version}")
  private String version;

  @Value("${swagger.api.controller.basepackage}")
  private String basePackage;

  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage(basePackage))
      .paths(PathSelectors.ant("/**")).build().apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder().title(title).description(description)
      .version(version).build();
  }

  @Override
  public void onApplicationEvent(ObjectMapperConfigured event) {
    event.getObjectMapper().registerModule(new VavrModule());
  }
}
