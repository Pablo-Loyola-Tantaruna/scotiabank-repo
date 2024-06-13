package com.codechallenge.scotiabank.model.api.searchstudent;

import lombok.Getter;
import lombok.Setter;

/**
 * <p> Clase que representa la respuesta de la b&uacute;squeda de alumnos.</p>
 * <p> Esta clase es utilizada para la respuesta de la b&uacute;squeda de alumnos.</p>
 * <p><b>Class</b>: SearchStudentResponse</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.model.api.searchstudent</p>
 * <p><b>Project</b>: codechallenge</p>
 * <p><b>Version</b>: 1.0.0</p>
 * <p><b>Creation Date</b>: 2024-06-11</p>
 * <p><b>Copyright</b>: Encora</p>
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
@Getter
@Setter
public class SearchStudentResponse {

  private String id_student;

  private String name_student;

  private String last_name_student;

  private int status_student;

  private int age_student;
}
