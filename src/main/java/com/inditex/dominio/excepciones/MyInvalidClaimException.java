package com.inditex.dominio.excepciones;

import io.jsonwebtoken.InvalidClaimException;

public class MyInvalidClaimException extends InvalidClaimException{

	private static final long serialVersionUID = 5L;

	public MyInvalidClaimException(String message) {
		super(null, null, null, null, message);
	}
}
