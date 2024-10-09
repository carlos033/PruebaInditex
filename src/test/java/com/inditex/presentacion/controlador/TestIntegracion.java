package com.inditex.presentacion.controlador;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.inditex.aplicacion.dto.PriceDTO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestIntegracion {

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void test() {
		LocalDateTime fechaHora = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
		LocalDateTime fechaHora2 = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
		LocalDateTime fechaHora3 = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
		LocalDateTime fechaHora4 = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
		LocalDateTime fechaHora5 = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

		testCase1(fechaHora);
		testCase1(fechaHora2);
		testCase1(fechaHora3);
		testCase1(fechaHora4);
		testCase1(fechaHora5);

	}

	void testCase1(LocalDateTime fechaHora) {
		DateTimeFormatter formatter =
		        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String fechaAConsultar = fechaHora.format(formatter);

		given().pathParam("brandId", "1").pathParam("productId", "35455")
		        .param("fechaAConsultar", fechaAConsultar).when()
		        .get("/prices/{brandId}/{productId}").then().statusCode(200);
		Response response = given().pathParam("brandId", "1")
		        .pathParam("productId", "35455").param("fechaAConsultar", fechaAConsultar)
		        .when().get("/prices/{brandId}/{productId}").andReturn();
		assertNotNull(response);
		PriceDTO priceDTO = response.as(PriceDTO.class);
		assertEquals("1", priceDTO.getBrandId());
		assertEquals("35455", priceDTO.getProductId());
		assertEquals(fechaAConsultar, priceDTO.getConsultationDate().format(formatter));

	}

}
