package com.inditex.estructura.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.inditex.dominio.excepciones.ExcepcionInditex;
import com.inditex.infraestructura.controlador.ControladorErrores;

@ExtendWith(MockitoExtension.class)
class ControladorErroresTest {
  @InjectMocks
  private ControladorErrores controladorErrores;

  @Test
  void testHandleExcepcionServicioWithStatusCode204() {
    ExcepcionInditex excepcion = new ExcepcionInditex(204, "Mensaje de error");

    ResponseEntity<String> response =
        controladorErrores.handleExcepcionServicio(excepcion);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertEquals("Mensaje de error", response.getBody());
  }

  @Test
  void testHandleExcepcionServicioWithStatusCode500() {
    ExcepcionInditex excepcion = new ExcepcionInditex(500, "Mensaje de error");

    ResponseEntity<String> response =
        controladorErrores.handleExcepcionServicio(excepcion);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals("Mensaje de error", response.getBody());
  }
}
