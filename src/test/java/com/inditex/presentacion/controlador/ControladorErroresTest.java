package com.inditex.presentacion.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.inditex.dominio.excepciones.DatabaseError;
import com.inditex.dominio.excepciones.ErrorResponse;
import com.inditex.dominio.excepciones.NotContentInditex;

@ExtendWith(MockitoExtension.class)
class ControladorErroresTest{

	@InjectMocks
	private ControladorErrores controladorErrores;

	@Mock
	private MethodArgumentNotValidException methodArgumentNotValidException;

	@Test
	void testItemNotFoundHandler() {
		// Crear una instancia de NotContentInditex
		NotContentInditex excepcion = new NotContentInditex(HttpStatus.NO_CONTENT, "Not content");
		ResponseEntity<Void> responseEntity = controladorErrores.itemNotFoundHandler(excepcion);

		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		assertNull(responseEntity.getBody());
	}



	@Test
	void testHandleMethodArgumentNotValid() {
		// Crear una instancia de MethodArgumentNotValidException simulada
		MethodArgumentNotValidException methodArgumentNotValidException =
				mock(MethodArgumentNotValidException.class);

		// Crear un objeto BindingResult simulado y configurar su
		// comportamiento
		BindingResult bindingResult = mock(BindingResult.class);

		// Configurar el comportamiento de getBindingResult() para devolver el
		// BindingResult simulado
		when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);

		// Llamar al método handleMethodArgumentNotValid
		ResponseEntity<ErrorResponse> result =
				controladorErrores.handleMethodArgumentNotValid(methodArgumentNotValidException);

		// Verificar que el resultado es un ResponseEntity con el mensaje de
		// error y HttpStatus.BAD_REQUEST
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		ErrorResponse errorResponse = (ErrorResponse) result.getBody();
		assertEquals("Validacion fallida", errorResponse.getMensaje());
	}

	@Test
	void testHandleMethoInternalError() {
		DatabaseError ex = new DatabaseError(500, "Mensaje de error");
		ResponseEntity<Map<String, Object>> response = controladorErrores.handleMethoInternalError(ex);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
		assertEquals(500, responseBody.get("status"));
		assertEquals("Internal Server Error", responseBody.get("error"));
		assertEquals("Ha ocurrido un error interno. Por favor, contacte con el administrador.",
				responseBody.get("message"));
		assertEquals(LocalDateTime.class, responseBody.get("timestamp").getClass());
	}

}
