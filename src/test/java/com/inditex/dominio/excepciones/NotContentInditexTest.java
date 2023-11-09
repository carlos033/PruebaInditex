package com.inditex.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotContentInditexTest {
  @Test
  void testNotContentInditexException() {
    // Datos de prueba
    int codigoError = 204;
    String mensaje = "Recurso no encontrado";

    // Asegurar que la excepción se lanza con los datos adecuados
    NotContentInditex exception = assertThrows(NotContentInditex.class, () -> {
      throw new NotContentInditex(codigoError, mensaje);
    });

    // Verificar si el mensaje y el código de error son los esperados
    assertEquals(mensaje, exception.getMessage());
    assertEquals(codigoError, exception.getCodigoError());
  }


}
