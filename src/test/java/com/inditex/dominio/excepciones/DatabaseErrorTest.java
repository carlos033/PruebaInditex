package com.inditex.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DatabaseErrorTest {
  @Test
  void testDatabaseErrorException() {
    // Datos de prueba
    int codigoError = 500;
    String mensaje = "Error de base de datos";

    // Asegurar que la excepción se lanza con los datos adecuados
    DatabaseError exception = assertThrows(DatabaseError.class, () -> {
      throw new DatabaseError(codigoError, mensaje);
    });

    // Verificar si el mensaje y el código de error son los esperados
    assertEquals(mensaje, exception.getMessage());
    assertEquals(codigoError, exception.getCodigoError());
  }
}
