package com.codechallenge.scotiabank.business.impl;

import com.codechallenge.scotiabank.business.StudentManagementService;
import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CreateStudentServiceImplTest {

  @Mock
  private StudentManagementService studentManagementService;

  @InjectMocks
  private CreateStudentServiceImpl createStudentServiceImpl;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateStudent_Success() {
    CreateStudentRequest createStudentRequest = studentRequestMock();

    when(studentManagementService.createStudent(createStudentRequest)).thenReturn(Mono.empty());

    createStudentServiceImpl.process(createStudentRequest).block();

    verify(studentManagementService, times(1)).createStudent(createStudentRequest);
  }

  @Test
  public void testCreateStudent_Fail() {
    CreateStudentRequest createStudentRequest = studentRequestMock();

    when(studentManagementService.createStudent(createStudentRequest)).thenReturn(Mono.error(new RuntimeException()));

    assertThrows(RuntimeException.class, () -> createStudentServiceImpl.process(createStudentRequest).block());
  }

  @Test
  public void testProcess_NullRequest() {
    CreateStudentRequest createStudentRequest = null;

    assertThrows(NullPointerException.class, () -> createStudentServiceImpl.process(createStudentRequest).block());
  }

  @Test
  public void testProcess_NullId() {
    CreateStudentRequest createStudentRequest = new CreateStudentRequest();
    createStudentRequest.setId_student(null);
    createStudentRequest.setName_student(null);
    createStudentRequest.setLast_name_student(null);
    createStudentRequest.setAge_student(-1);
    createStudentRequest.setStatus_student(-1);

    assertThrows(NullPointerException.class, () -> createStudentServiceImpl.process(createStudentRequest).block());
  }

  @Test
  public void testProcess_EmptyValues() {
    CreateStudentRequest createStudentRequest = new CreateStudentRequest();
    createStudentRequest.setId_student("");
    createStudentRequest.setName_student("");
    createStudentRequest.setLast_name_student("");
    createStudentRequest.setAge_student(0);
    createStudentRequest.setStatus_student(0);

    assertThrows(NullPointerException.class, () -> createStudentServiceImpl.process(createStudentRequest).block());
  }

  private CreateStudentRequest studentRequestMock() {
    CreateStudentRequest createStudentRequest = new CreateStudentRequest();
    createStudentRequest.setId_student("1");
    createStudentRequest.setName_student("John");
    createStudentRequest.setLast_name_student("Lenon");
    createStudentRequest.setAge_student(20);
    createStudentRequest.setStatus_student(1);
    return createStudentRequest;
  }
}
