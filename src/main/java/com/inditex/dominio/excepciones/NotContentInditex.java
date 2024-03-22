package com.inditex.dominio.excepciones;

import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotContentInditex extends RuntimeException{

	private static final long serialVersionUID = 3L;
	private final HttpStatus codigoError;
	private final String mensaje;

	public NotContentInditex(HttpStatus noContent, String mensaje) {
		this.codigoError = noContent;
		this.mensaje = mensaje;
	}
}
