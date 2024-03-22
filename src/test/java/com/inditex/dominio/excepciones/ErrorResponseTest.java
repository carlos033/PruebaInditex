package com.inditex.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ErrorResponseTest {
  @Test
  void testConstructorAndGetters() {
    // Crear datos de prueba
    String mensaje = "Error en la solicitud";
    List<String> detalles = Arrays.asList("Detalle 1", "Detalle 2");

    // Crear instancia de ErrorResponse
    ErrorResponse errorResponse = new ErrorResponse(mensaje, detalles);

    // Verificar que los valores se hayan establecido correctamente
    assertEquals(mensaje, errorResponse.getMensaje());
    assertEquals(detalles, errorResponse.getDetalles());
  }

  @Test
  void testSetterAndGetters() {
    // Crear instancia de ErrorResponse
    ErrorResponse errorResponse = new ErrorResponse();

    // Establecer valores utilizando setters
    String mensaje = "Nuevo mensaje de error";
    List<String> detalles = Arrays.asList("Nuevo detalle 1", "Nuevo detalle 2");

    errorResponse.setMensaje(mensaje);
    errorResponse.setDetalles(detalles);

    // Verificar que los valores se hayan establecido correctamente
    assertEquals(mensaje, errorResponse.getMensaje());
    assertEquals(detalles, errorResponse.getDetalles());
  }

  @Test
  void testNoArgsConstructor() {
    // Crear instancia utilizando constructor sin argumentos
    ErrorResponse errorResponse = new ErrorResponse();

    // Verificar que la instancia no sea nula
    assertNotNull(errorResponse);
  }
}
