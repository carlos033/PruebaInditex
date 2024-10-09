package com.inditex.dominio.excepciones;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotContentInditex extends RuntimeException{

	private static final long serialVersionUID = 2L;
	private final HttpStatus codigoError;
	private final String mensaje;

	public NotContentInditex(HttpStatus noContent, String mensaje) {
		this.codigoError = noContent;
		this.mensaje = mensaje;
	}
}
