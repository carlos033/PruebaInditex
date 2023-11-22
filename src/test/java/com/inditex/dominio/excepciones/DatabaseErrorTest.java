package com.inditex.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

  @Test
  void testEquals() {
    DatabaseError instance1 = new DatabaseError(500, "Internal Server Error");
    DatabaseError instance2 = new DatabaseError(500, "Internal Server Error");

    // Verificar que las instancias sean iguales
    assertEquals(instance1, instance2);

    // Modificar una propiedad y verificar que las instancias sean diferentes
    DatabaseError instance3 = new DatabaseError(501, "Internal Server Error");
    assertNotEquals(instance1, instance3);
  }

  @Test
  void testHashCode() {
    DatabaseError instance1 = new DatabaseError(500, "Internal Server Error");
    DatabaseError instance2 = new DatabaseError(500, "Internal Server Error");

    // Verificar que los códigos hash sean iguales
    assertEquals(instance1.hashCode(), instance2.hashCode());
  }
}
