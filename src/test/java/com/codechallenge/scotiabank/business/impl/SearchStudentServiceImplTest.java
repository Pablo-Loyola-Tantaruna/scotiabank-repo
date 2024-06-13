package com.codechallenge.scotiabank.business.impl;

import com.codechallenge.scotiabank.business.StudentManagementService;
import com.codechallenge.scotiabank.model.api.searchstudent.SearchStudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchStudentServiceImplTest {

  @Mock
  private StudentManagementService studentManagementService;

  @InjectMocks
  private SearchStudentServiceImpl searchStudentServiceImpl;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testProcess_Success() {
    SearchStudentResponse searchStudentResponse = new SearchStudentResponse();
    searchStudentResponse.setId_student("1");
    searchStudentResponse.setName_student("John");
    searchStudentResponse.setLast_name_student("Lenon");
    searchStudentResponse.setAge_student(20);
    searchStudentResponse.setStatus_student(1);
    when(studentManagementService.getActiveStudents()).thenReturn(Flux.just(Mono.just(searchStudentResponse)));

    Flux<Mono<SearchStudentResponse>> result = searchStudentServiceImpl.process();

    verify(studentManagementService, times(1)).getActiveStudents();
  }

  @Test
  public void testProcess_Error() {
    when(studentManagementService.getActiveStudents()).thenReturn(Flux.error(new RuntimeException("Error")));

    assertThrows(RuntimeException.class, () -> searchStudentServiceImpl.process().blockFirst());
  }
}