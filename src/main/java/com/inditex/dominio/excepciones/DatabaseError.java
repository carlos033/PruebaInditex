package com.inditex.dominio.excepciones;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseError extends RuntimeException {

  private static final long serialVersionUID = 4L;
  private final int codigoError;

  public DatabaseError(int codigoError, String msg) {
    super(msg);
    this.codigoError = codigoError;
  }

}
