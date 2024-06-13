package com.codechallenge.scotiabank.business.impl;

import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import com.codechallenge.scotiabank.model.api.searchstudent.SearchStudentResponse;
import com.codechallenge.scotiabank.model.cache.StudentCache;
import com.codechallenge.scotiabank.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentManagementServiceImplTest {

  @Mock
  private StudentRepository studentRepository;

  @InjectMocks
  private StudentManagementServiceImpl studentManagementService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateStudent() {
    // Arrange
    CreateStudentRequest request = new CreateStudentRequest();
    request.setId_student("1");
    request.setName_student("John");
    request.setLast_name_student("Doe");
    request.setAge_student(20);
    request.setStatus_student(1);

    when(studentRepository.existsStudent(any())).thenReturn(Mono.just(false));
    when(studentRepository.save(any())).thenReturn(Mono.empty());

    // Act
    Mono<Void> result = studentManagementService.createStudent(request);

    // Assert
    StepVerifier.create(result)
            .expectComplete()
            .verify();
  }

  @Test
  void testGetActiveStudents() {
    // Arrange
    StudentCache studentCache = StudentCache.builder()
            .id_student("1")
            .name_student("John")
            .last_name_student("Doe")
            .age_student(20)
            .status_student(1)
            .build();

    when(studentRepository.findAll()).thenReturn(Flux.just(studentCache));

    // Act
    Flux<Mono<SearchStudentResponse>> result = studentManagementService.getActiveStudents();

    // Assert
    StepVerifier.create(result)
            .expectNextMatches(mono -> {
              StepVerifier.create(mono)
                      .expectNextMatches(response ->
                              response.getId_student().equals("1") &&
                                      response.getName_student().equals("John") &&
                                      response.getLast_name_student().equals("Doe") &&
                                      response.getAge_student() == 20 &&
                                      response.getStatus_student() == 1)
                      .verifyComplete();
              return true;
            })
            .verifyComplete();
  }
}