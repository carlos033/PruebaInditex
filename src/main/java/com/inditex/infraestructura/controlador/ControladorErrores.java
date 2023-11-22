package com.inditex.infraestructura.controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static final Logger logger = LoggerFactory.getLogger(ControladorErrores.class);

  @ExceptionHandler(NotContentInditex.class)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> itemNotFoundHandler(NotContentInditex ex) {
    logger.error("Error 204: {}", ex.getMensaje());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    logger.error("error 400: {}", ex.getMessage());

    List<String> listaDetalles = new ArrayList<>();
    ex.getBindingResult().getAllErrors()
        .forEach(error -> listaDetalles.add(error.getDefaultMessage()));
    ErrorResponse error = new ErrorResponse("Validacion fallida", listaDetalles);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DatabaseError.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleMethoInternalError(DatabaseError ex) {
    logger.error("Error 500: {}", ex.getMessage());

    Map<String, Object> body =
        Map.of("status", 500, "error", "Internal Server Error", "timestamp", LocalDateTime.now(),
            "message", "Ha ocurrido un error interno. Por favor, contacte con el administrador.");

    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
