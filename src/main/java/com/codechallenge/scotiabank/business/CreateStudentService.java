package com.codechallenge.scotiabank.business;

import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import reactor.core.publisher.Mono;

/**
 * <p> Interface que permite la creaci&oacute;n de alumnos.</p>
 * <p> Esta interfaz es implementada por la clase que se encarga
 * de la creación de alumno.</p>
 * <p><b>Class</b>: CreateStudentService</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.business</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Creation Date</b>: 2024-06-11</p>
 * <p><b>Copyright</b>: Encora</p>
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
public interface CreateStudentService {

  /**
  * M&eacute;todo que permite la creaci&oacute;n de un alumno.
  *
  * @param request Informaci&oacute;n del alumno a crear.
  * @return Completable
  */
  Mono<Void> process(CreateStudentRequest request);
}
