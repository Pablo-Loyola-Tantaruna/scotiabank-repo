package com.codechallenge.scotiabank.business.impl;

import com.codechallenge.scotiabank.business.CreateStudentService;
import com.codechallenge.scotiabank.business.SearchStudentService;
import com.codechallenge.scotiabank.business.StudentManagementService;
import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import com.codechallenge.scotiabank.model.api.searchstudent.SearchStudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p> Clase que implementa la interfaz SearchStudentService.</p>
 * <p> Esta clase se encarga de la obtenci&oacute;n de alumnos.</p>
 * <p><b>Class</b>: SearchStudentServiceImpl</p>
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
public class SearchStudentServiceImpl implements SearchStudentService {

  private final StudentManagementService studentManagementService;

  @Override
  public Flux<Mono<SearchStudentResponse>> process() {
    log.info("SearchStudentServiceImpl.process");
    return studentManagementService.getActiveStudents()
       .doOnError(e -> log.error("Error searching student", e));
  }
}

