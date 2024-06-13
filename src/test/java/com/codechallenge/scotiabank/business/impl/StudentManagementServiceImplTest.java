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
    request.setId("1");
    request.setNombre("John");
    request.setApellido("Doe");
    request.setEdad(20);
    request.setEstado("ACTIVO");

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
    Flux<SearchStudentResponse> result = studentManagementService.getActiveStudents();

    // Assert
    StepVerifier.create(result)
            .expectNextMatches(response -> response.getId().equals("1") &&
                    response.getNombre().equals("John") &&
                    response.getApellido().equals("Doe") &&
                    response.getEdad() == 20 &&
                    response.getEstado().equalsIgnoreCase("ACTIVO")).verifyComplete();
  }
}