package com.inditex.infraestructura.controlador;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.inditex.dominio.excepciones.DatabaseError;
import com.inditex.dominio.excepciones.ErrorResponse;
import com.inditex.dominio.excepciones.NotContentInditex;

@RestControllerAdvice
public class ControladorErrores {

  @ExceptionHandler(NotContentInditex.class)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Object> itemNotFoundHandler(NotContentInditex ex) {
    return new ResponseEntity<>(ex, HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex) {
    List<String> listaDetalles = new ArrayList<>();
    ex.getBindingResult().getAllErrors()
        .forEach(error -> listaDetalles.add(error.getDefaultMessage()));
    ErrorResponse error =
        new ErrorResponse("Validacion fallida", listaDetalles);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DatabaseError.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleMethoInternalError(
      MethodArgumentNotValidException ex) {
    List<String> listaDetalles = new ArrayList<>();
    ex.getBindingResult().getAllErrors()
        .forEach(error -> listaDetalles.add(error.getDefaultMessage()));
    ErrorResponse error = new ErrorResponse("BD caida", listaDetalles);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
