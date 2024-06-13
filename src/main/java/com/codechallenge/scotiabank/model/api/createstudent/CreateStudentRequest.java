package com.codechallenge.scotiabank.model.api.createstudent;


import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.intellij.lang.annotations.Pattern;

/**
 * <p> Clase modelo que representa la petici&oacute;n de creaci&oacute;n de un alumno.</p>
 * <p> Esta clase es utilizada para la representaci&oacute;n de la
 * petici&oacute;n de creaci&oacute;n de un alumno.</p>
 * <p><b>Class</b>: CreateStudentRequest</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank</p>
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
@Getter
@Setter
public class CreateStudentRequest {

  @ApiParam(value = "Identificador del estudiante",
            example = "1",
            type = "int",
            required = true)
  private String id;

  @Pattern("^[a-zA-Z]$")
  @ApiParam(value = "Nombre del estudiante",
          example = "Pablo",
          type = "string",
          required = true)
  private String nombre;

  @Pattern("^[a-zA-Z]$")
  @ApiParam(value = "Apellido del estudiante",
          example = "Loyola",
          type = "string",
          required = true)
  private String apellido;

  @ApiParam(value = "Estado del estudiante",
          type = "string",
          allowableValues = "ACTIVO, INACTIVO",
          example = "1",
          required = true)
  private String estado;

  @ApiParam(value = "Edad del estudiante",
          type = "int",
          example = "10",
          required = true)
  private int edad;
}
