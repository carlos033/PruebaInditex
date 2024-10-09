package com.inditex.dominio.excepciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class TokenValidationException extends RuntimeException{

	private static final long serialVersionUID = 3L;

	public TokenValidationException(String message) {
		super(message);
	}

}
