package com.inditex.dominio.excepciones;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExcepcionInditex extends Exception {

  private static final long serialVersionUID = 3L;
  private final int codigoError;

  public ExcepcionInditex(int codigoError, String msg) {
    super(msg);
    this.codigoError = codigoError;
  }
}
