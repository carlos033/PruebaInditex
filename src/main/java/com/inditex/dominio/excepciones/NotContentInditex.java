package com.inditex.dominio.excepciones;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotContentInditex extends RuntimeException {

  private static final long serialVersionUID = 3L;
  private final int codigoError;
  private final String mensaje;

  public NotContentInditex(int codigoError, String mensaje) {
    this.codigoError = codigoError;
    this.mensaje = mensaje;
  }
}
