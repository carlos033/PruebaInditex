package com.inditex.infraestructura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.inditex.dominio.excepciones.ExcepcionInditex;

@ControllerAdvice
public class ControladorErrores {

  @ExceptionHandler(ExcepcionInditex.class)
  public ResponseEntity<String> handleExcepcionServicio(ExcepcionInditex ex) {
    HttpStatus status = (ex.getCodigoError() == 204) ? HttpStatus.NO_CONTENT
        : HttpStatus.INTERNAL_SERVER_ERROR;

    return new ResponseEntity<>(ex.getMessage(), status);
  }
}
