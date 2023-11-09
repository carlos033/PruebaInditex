package com.inditex.estructura.controlador;

import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestIntegracion {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void testCase1() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String fechaAConsultar = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaAConsultar", fechaAConsultar).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(200);
  }

  @Test
  void testCase2() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String fechaAConsultar = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaAConsultar", fechaAConsultar).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(200);
  }

  @Test
  void testCase3() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String fechaAConsultar = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaAConsultar", fechaAConsultar).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(200);
  }

  @Test
  void testCase4() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String fechaAConsultar = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaAConsultar", fechaAConsultar).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(200);

  }

  @Test
  void testCase5() {
    LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String fechaAConsultar = fechaHora.format(formatter);

    given().pathParam("brandId", "1").pathParam("productId", "35455")
        .param("fechaAConsultar", fechaAConsultar).when()
        .get("/prices/{brandId}/{productId}").then().statusCode(200);
  }
}
