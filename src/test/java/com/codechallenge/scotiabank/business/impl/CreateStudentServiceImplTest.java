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
    createStudentRequest.setId(null);
    createStudentRequest.setNombre(null);
    createStudentRequest.setApellido(null);
    createStudentRequest.setEdad(-1);
    createStudentRequest.setEstado("PABLO");

    assertThrows(NullPointerException.class, () -> createStudentServiceImpl.process(createStudentRequest).block());
  }

  @Test
  public void testProcess_EmptyValues() {
    CreateStudentRequest createStudentRequest = new CreateStudentRequest();
    createStudentRequest.setId("");
    createStudentRequest.setNombre("");
    createStudentRequest.setApellido("");
    createStudentRequest.setEdad(0);
    createStudentRequest.setEstado("");

    assertThrows(NullPointerException.class, () -> createStudentServiceImpl.process(createStudentRequest).block());
  }

  private CreateStudentRequest studentRequestMock() {
    CreateStudentRequest createStudentRequest = new CreateStudentRequest();
    createStudentRequest.setId("1");
    createStudentRequest.setNombre("John");
    createStudentRequest.setApellido("Lenon");
    createStudentRequest.setEdad(20);
    createStudentRequest.setEstado("ACTIVO");
    return createStudentRequest;
  }
}
