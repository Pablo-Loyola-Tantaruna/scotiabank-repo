package com.codechallenge.scotiabank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Esta clase es una clase de configuración de Swagger.</p>
 * <p>Clase de configuración de Swagger</p>
 * <p><b>Class</b>: SwaggerConfig</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.config</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Creation Date</b>: 2024-06-10</p>
 * <p><b>Company</b>: Encora</p>
 * <p>@author Pablo Sergio Loyola Tantaruna</p>
 * <div>
 *     <u>Developed by</u>:
 *     <ul>
 *         <li>Pablo Sergio Loyola Tantaruna</li>
 *     </ul>
 * </div>
 * <div>
 *     <u>Reviewed by</u>:
 *     <ul>
 *         <li>Pablo Sergio Loyola Tantaruna</li>
 *     </ul>
 * </div>
 * <p><b>Version</b>: 1.0.0</p>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * <p>Este método crea un bean de tipo Docket que se encarga de la configuración de Swagger.</p>
   *
   * @return Docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.codechallenge.scotiabank"))
      .paths(PathSelectors.any())
      .build();
  }
}
