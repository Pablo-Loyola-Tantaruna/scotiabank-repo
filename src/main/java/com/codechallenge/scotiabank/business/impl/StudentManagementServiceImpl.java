package com.codechallenge.scotiabank.business.impl;

import com.codechallenge.scotiabank.business.StudentManagementService;
import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import com.codechallenge.scotiabank.model.api.searchstudent.SearchStudentResponse;
import com.codechallenge.scotiabank.model.cache.StudentCache;
import com.codechallenge.scotiabank.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <p> Clase que implementa la interfaz StudentManagementService.</p>
 * <p> Esta Clase se encarga de gestionar las operaciones de alumnos.</p>
 * <p><b>Class</b>: StudentManagementServiceImpl</p>
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
public class StudentManagementServiceImpl implements StudentManagementService {

  private final StudentRepository studentRepository;

  /**
   * M&eacute;todo que permite la creaci&oacute;n de
   * un alumno.
   *
   * @param request Informaci&oacute;n del alumno.
   * @return void mono
   */
  @Override
  public Mono<Void> createStudent(CreateStudentRequest request) {
    log.info("StudentManagementServiceImpl.createStudent");
    return Mono.just(request)
           .filterWhen(r -> existsStudent(r.getId_student()).map(exists -> !exists))
           .switchIfEmpty(Mono.error(new RuntimeException("Student already exists")))
           .flatMap(this::saveStudent)
           .then();
  }

  private Mono<Boolean> existsStudent(String studentId) {
    log.info("StudentManagementServiceImpl.existsStudent");
    return studentRepository.existsStudent(studentId);
  }

  private Mono<Void> saveStudent(CreateStudentRequest request) {
    log.info("StudentManagementServiceImpl.saveStudent");
    StudentCache studentCache = StudentCache.builder()
            .id_student(request.getId_student())
            .name_student(request.getName_student())
            .last_name_student(request.getLast_name_student())
            .age_student(request.getAge_student())
            .status_student(request.getStatus_student())
            .build();

    return studentRepository.save(studentCache)
           .doOnSuccess(success -> log.info("Student saved successfully"))
           .doOnError(error -> log.error("Error saving student: " + error))
           .then();
  }

  /**
   * M&eacute;todo que permite la obtenci&oacute;n de
   * los alumnos activos.
   *
   * @return {@link SearchStudentResponse}
   */
  @Override
  public Flux<Mono<SearchStudentResponse>> getActiveStudents() {
    log.info("StudentManagementServiceImpl.getActiveStudents");
    return studentRepository.findAll()
           .flatMap(Flux::just)
           .filter(studentCache -> studentCache.getStatus_student() == 1)
           .map(studentCache -> Mono.just(cacheToResponse(studentCache)));
  }

  private SearchStudentResponse cacheToResponse(StudentCache studentCache) {
    SearchStudentResponse searchStudentResponse = new SearchStudentResponse();
    searchStudentResponse.setId_student(studentCache.getId_student());
    searchStudentResponse.setName_student(studentCache.getName_student());
    searchStudentResponse.setLast_name_student(studentCache.getLast_name_student());
    searchStudentResponse.setAge_student(studentCache.getAge_student());
    searchStudentResponse.setStatus_student(studentCache.getStatus_student());
    return searchStudentResponse;
  }
}
