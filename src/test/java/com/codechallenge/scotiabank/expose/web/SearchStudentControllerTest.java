package com.codechallenge.scotiabank.expose.web;

import com.codechallenge.scotiabank.business.SearchStudentService;
import com.codechallenge.scotiabank.model.api.searchstudent.SearchStudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuples;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchStudentControllerTest {

  @Mock
  private SearchStudentService searchStudentService;

  @InjectMocks
  private SearchStudentController searchStudentController;

  private SearchStudentResponse student1;
  private SearchStudentResponse student2;

  @BeforeEach
  void setUp() {
    student1 = searchStudentResponseMock();
    student2 = searchStudentResponseMock();
  }

  @Test
  void testSearchStudent_Success() {
    when(searchStudentService.process()).thenReturn(Flux.just(student1, student2));

    Flux<SearchStudentResponse> result = searchStudentController.searchStudent();

    StepVerifier.create(result)
        .expectNext(student1)
        .expectNext(student2)
        .verifyComplete();

    verify(searchStudentService, times(1)).process();
  }

  @Test
  void testSearchStudent_Error() {
    when(searchStudentService.process()).thenReturn(Flux.error(new RuntimeException("Unexpected Error")));

    Flux<SearchStudentResponse> result = searchStudentController.searchStudent();

    StepVerifier.create(result)
        .expectErrorMatches(throwable -> throwable instanceof RuntimeException && throwable.getMessage().equals("Unexpected Error"))
        .verify();

    verify(searchStudentService, times(1)).process();
  }

  @Test
  void testSearchStudent_NoStudentsAvailable() {
    when(searchStudentService.process()).thenReturn(Flux.empty());

    Flux<SearchStudentResponse> result = searchStudentController.searchStudent();

    StepVerifier.create(result)
        .verifyComplete();

    verify(searchStudentService, times(1)).process();
  }

  private SearchStudentResponse searchStudentResponseMock() {
    SearchStudentResponse searchStudentResponse = new SearchStudentResponse();
    searchStudentResponse.setId("1");
    searchStudentResponse.setNombre("John");
    searchStudentResponse.setApellido("Doe");
    searchStudentResponse.setEdad(20);
    searchStudentResponse.setEstado("ACTIVO");
    return searchStudentResponse;
  }
}


