package com.inditex.dominio.excepciones;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseError extends InternalError {

	private static final long serialVersionUID = 1L;
	private final int codigoError;

	public DatabaseError(int codigoError, String msg) {
		super(msg);
		this.codigoError = codigoError;
	}
}
