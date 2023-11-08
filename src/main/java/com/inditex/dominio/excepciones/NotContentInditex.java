package com.inditex.dominio.excepciones;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotContentInditex extends RuntimeException {

  private static final long serialVersionUID = 3L;
  private final int codigoError;

  public NotContentInditex(int codigoError, String msg) {
    super(msg);
    this.codigoError = codigoError;
  }
}
