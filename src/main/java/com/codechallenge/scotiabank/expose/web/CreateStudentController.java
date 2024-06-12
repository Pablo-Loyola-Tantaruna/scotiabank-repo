package com.codechallenge.scotiabank.expose.web;

import com.codechallenge.scotiabank.business.CreateStudentService;
import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * <p> Controlador principal que expone la funcionalidad de creación de alumnos
 * a tra&eacute;s de Http/rest para las aplicaciones clientes.</p>
 * <p><b>Class</b>: CreateStudentController</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.expose.web</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Company</b>: Encora</p>
 * <p><b>Creation Date</b>: 2024-06-10</p>
 * <p>@author Pablo Sergio Loyola Tantaruna</p>
 * <div>
 *   <u>Developed by</u>:
 *   <ul>
 *     <li>Pablo Sergio Loyola Tantaruna</li>
 *   </ul>
 * </div>
 * <div>
 *   <u>Reviewed by</u>:
 *   <ul>
 *     <li>Pablo Sergio Loyola Tantaruna</li>
 *   </ul>
 * </div>
 * <p><b>Version</b>: 1.0.0</p>
 */

@RefreshScope
@RestController
@Tag(name = "CreateStudent")
@RequestMapping("/new-student")
@Slf4j
@RequiredArgsConstructor
public class CreateStudentController {

  private final CreateStudentService createStudentService;
  @InitBinder
  public void initBinder(DataBinder dataBinder) {
    dataBinder.setDisallowedFields();
  }

  /**
   * Met&oacute;do que permite la creaci&oacute;n de un alumno.
   *
   * @param createStudentRequest the request to create a student
   * @return void mono
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  /*@Operation(tags = "CreateStudentResponse",
          summary = "Se cre&oacute; un nuevo estudiante correctamente",
          method = "POST",
          description = "classpath:swagger/notes/create-student.md",
          responses = {
                  @ApiResponse(responseCode = "201",
                          description = "El producto fue creado correctamente"),
                  @ApiResponse(responseCode = "400",
                          description = "La solicitud es incorrecta",
                          content = @Content(schema = @Schema(implementation = ApiException.class))),
                  @ApiResponse(responseCode = "500",
                          description = "Error interno del servidor",
                          content = @Content(schema = @Schema(implementation = ApiException.class))),
                  @ApiResponse(responseCode = "503",
                          description = "Servicio no disponible",
                          content = @Content(schema = @Schema(implementation = ApiException.class))) })*/
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> createStudent(
          @ParameterObject
          @RequestBody CreateStudentRequest createStudentRequest) {
    return createStudentService.process(createStudentRequest)
            .doOnError(error -> log.error("Error creating student", error))
            .doOnSuccess(e -> log.info("Student created successfully"))
            .doOnTerminate(() -> log.info("Student creation process finished"))
            .subscribeOn(Schedulers.boundedElastic());
  }
}
