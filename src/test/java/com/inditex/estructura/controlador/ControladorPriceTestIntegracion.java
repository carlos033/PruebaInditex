package com.inditex.estructura.controlador;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControladorPriceTestIntegracion {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void testIntegrationCase1() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String fechaInicio = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaInicio", fechaInicio).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(204);
  }

  @Test
  void testIntegrationCase2() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String fechaInicio = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaInicio", fechaInicio).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(204);
  }

  @Test
  void testIntegrationCase3() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String fechaInicio = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaInicio", fechaInicio).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(204);
  }

  @Test
  void testIntegrationCase4() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String fechaInicio = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaInicio", fechaInicio).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(204);

  }

  @Test
  void testIntegrationCase5() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String fechaInicio = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaInicio", fechaInicio).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(204);
  }

  @Test
  void testIntegrationCase6() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 15, 16, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    String fechaInicio = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaInicio", fechaInicio).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(200)
        .contentType(ContentType.JSON).body("size()", greaterThan(0));
  }
}
