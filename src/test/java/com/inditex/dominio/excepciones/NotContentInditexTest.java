package com.inditex.dominio.excepciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class NotContentInditexTest{
	@Test
	void testNotContentInditexException() {
		// Datos de prueba
		String mensaje = "Recurso no encontrado";

		// Asegurar que la excepción se lanza con los datos adecuados
		NotContentInditex exception = assertThrows(NotContentInditex.class, () -> {
			throw new NotContentInditex(HttpStatus.NO_CONTENT, mensaje);
		});

		// Verificar si el mensaje y el código de error son los esperados
		assertEquals(mensaje, exception.getMensaje());
		assertEquals(HttpStatus.NO_CONTENT, exception.getCodigoError());
	}


	@Test
	void testEquals() {
		NotContentInditex instance1 = new NotContentInditex(HttpStatus.NO_CONTENT, "Not content");
		NotContentInditex instance2 = new NotContentInditex(HttpStatus.NO_CONTENT, "Not content");

		// Verificar que las instancias sean iguales
		assertEquals(instance1, instance2);

		// Modificar una propiedad y verificar que las instancias sean diferentes
		NotContentInditex instance3 = new NotContentInditex(HttpStatus.NOT_FOUND, "Not content");
		assertNotEquals(instance1, instance3);
	}

	@Test
	void testHashCode() {
		NotContentInditex instance1 = new NotContentInditex(HttpStatus.NO_CONTENT, "Not content");
		NotContentInditex instance2 = new NotContentInditex(HttpStatus.NO_CONTENT, "Not content");

		// Verificar que los códigos hash sean iguales
		assertEquals(instance1.hashCode(), instance2.hashCode());
	}


}
