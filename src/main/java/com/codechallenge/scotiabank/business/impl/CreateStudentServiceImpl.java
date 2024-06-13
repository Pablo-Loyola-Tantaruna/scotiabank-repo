package com.codechallenge.scotiabank.business.impl;

import com.codechallenge.scotiabank.business.CreateStudentService;
import com.codechallenge.scotiabank.business.StudentManagementService;
import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * <p> Clase que implementa la interfaz CreateStudentService.</p>
 * <p> Esta clase se encarga de gestionar la creación de alumnos.</p>
 * <p><b>Class</b>: CreateStudentServiceImpl</p>
 * <p><b>Package</b>: com.codechallenge.scotiabank.business.impl</p>
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

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateStudentServiceImpl implements CreateStudentService {

  private final StudentManagementService studentManagementService;

  @Override
  public Mono<Void> process(CreateStudentRequest requestStudent) {
    log.info("CreateStudentServiceImpl.process");
    return studentManagementService.createStudent(requestStudent)
           .doOnError(e -> log.error("Error creating student", e))
           .onErrorResume(e -> Mono.error(new RuntimeException("Error creating student")))
           .doOnSuccess(e -> log.info("Student created successfully"))
           .then();
  }
}
