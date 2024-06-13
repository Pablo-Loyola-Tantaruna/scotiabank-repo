package com.codechallenge.scotiabank.expose.web;

import com.codechallenge.scotiabank.business.CreateStudentService;
import com.codechallenge.scotiabank.model.api.createstudent.CreateStudentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class CreateStudentControllerTest {
  @Mock
  private CreateStudentService createStudentService;

  @InjectMocks
  private CreateStudentController createStudentController;

  private WebTestClient webTestClient;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    webTestClient = WebTestClient.bindToController(createStudentController).build();
  }

  @Test
  void testCreateStudent_DuplicateId() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setId("1");

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setId(null);

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest2() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setNombre(null);

    webTestClient.post()
    .uri("/new-student")
    .bodyValue(createStudentRequest)
    .exchange()
    .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest3() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setApellido(null);

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest4() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setEstado("PABLO");

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest5() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setEdad(0);

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest6() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setEdad(0);
    createStudentRequest.setEstado("INACTIVO");

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest7() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setNombre("");

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest8() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setApellido("");

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest9() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setId("");

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest10() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setId("   ");

    webTestClient.post()
            .uri("/new-student")
            .bodyValue(createStudentRequest)
            .exchange()
            .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest11() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setNombre("   ");

    webTestClient.post()
            .uri("/new-student")
            .bodyValue(createStudentRequest)
            .exchange()
            .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest12() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setApellido("   ");

    webTestClient.post()
            .uri("/new-student")
            .bodyValue(createStudentRequest)
            .exchange()
            .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest14() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setNombre("1234567890123456789012345678901234567890"); // More than 30 characters

    webTestClient.post()
            .uri("/new-student")
            .bodyValue(createStudentRequest)
            .exchange()
            .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest15() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setApellido("1234567890123456789012345678901234567890"); // More than 30 characters

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_EmptyRequest() {
    CreateStudentRequest createStudentRequest = new CreateStudentRequest();

    webTestClient.post()
      .uri("/new-student")
      .bodyValue(createStudentRequest)
      .exchange()
      .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest19() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setEdad(-1);

    webTestClient.post()
            .uri("/new-student")
            .bodyValue(createStudentRequest)
            .exchange()
            .expectStatus().is5xxServerError();
  }

  @Test
  void testCreateStudent_InvalidRequest20() {
    CreateStudentRequest createStudentRequest = createStudentRequestMock();
    createStudentRequest.setEstado("PABLO"); // Negative status

    webTestClient.post()
            .uri("/new-student")
            .bodyValue(createStudentRequest)
            .exchange()
            .expectStatus().is5xxServerError();
  }

  private CreateStudentRequest createStudentRequestMock() {
    CreateStudentRequest createStudentRequest = new CreateStudentRequest();
    createStudentRequest.setId("1");
    createStudentRequest.setNombre("Pablo");
    createStudentRequest.setApellido("Loyola");
    createStudentRequest.setEstado("ACTIVO");
    createStudentRequest.setEdad(25);
    return createStudentRequest;
  }
}
