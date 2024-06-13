package com.codechallenge.scotiabank.expose.web;

import com.codechallenge.scotiabank.business.CreateStudentService;
import com.codechallenge.scotiabank.business.SearchStudentService;
import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import com.codechallenge.scotiabank.model.api.searchstudent.SearchStudentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * <p> Controlador principal que expone la funcionalidad de b&uacute;squeda de alumnos
 * a tra&eacute;s de Http/rest para las aplicaciones clientes.</p>
 * <p><b>Class</b>: SearchStudentController</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.expose.web</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Company</b>: Encora</p>
 * <p><b>Creation Date</b>: 2024-06-10</p>
 * <p>@author Pablo Sergio Loyola Tantaruna</p>
 * <div>
 *   <u>Developed by</u>:
 *   <ul>
 *   <li>Pablo Sergio Loyola Tantaruna</li>
 *   </ul>
 * </div>
 * <div>
 *   <u>Reviewed by</u>:
 *   <ul>
 *   <li>Pablo Sergio Loyola Tantaruna</li>
 *   </ul>
 * </div>
 * <p><b>Version</b>: 1.0.0</p>
 */

@RestController
@RefreshScope
@Tag(name = "SearchStudent")
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class SearchStudentController {

  private final SearchStudentService searchStudentService;

  @InitBinder
  public void initBinder(DataBinder dataBinder) {
    dataBinder.setDisallowedFields();
  }

  /**
   * Met&oacute;do que permite la obtenci&oacute;n de los alumnos.
   *
   * @return {@link SearchStudentResponse}
   */
  @GetMapping
  @Operation(tags = "SearchStudentResponse",
      summary = "Se obtuvo la lista de estudiantes correctamente",
      method = "GET",
      description = "classpath:swagger/notes/search-student.md",
      responses = {
        @ApiResponse(responseCode = "200",
          description = "Se obtuvo la lista de estudiantes correctamente"),
        @ApiResponse(responseCode = "400",
          description = "La solicitud es incorrecta",
          content = @Content(schema = @Schema(implementation = Exception.class))),
        @ApiResponse(responseCode = "500",
          description = "Error interno del servidor",
          content = @Content(schema = @Schema(implementation = Exception.class))),
        @ApiResponse(responseCode = "503",
          description = "Servicio no disponible",
          content = @Content(schema = @Schema(implementation = Exception.class))) })
  @ResponseStatus(HttpStatus.OK)
  public Flux<Mono<SearchStudentResponse>> searchStudent() {
    return searchStudentService.process()
        .doOnError(error -> log.error("Error getting students", error))
        .doOnComplete(() -> log.info("Student getting process successfully completed"))
        .doOnTerminate(() -> log.info("Student getting process finished"))
        .subscribeOn(Schedulers.boundedElastic());
  }
}
