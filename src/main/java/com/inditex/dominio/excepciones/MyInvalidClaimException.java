package com.inditex.dominio.excepciones;

import com.auth0.jwt.exceptions.InvalidClaimException;

public class MyInvalidClaimException extends InvalidClaimException{

	private static final long serialVersionUID = 5L;

	public MyInvalidClaimException(String message) {
		super(message);
	}
}
