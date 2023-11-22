package com.inditex.infraestructura.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex) {
    logger.error("Error 400: {}", ex.getMessage());

    List<String> detalles =
        ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
    
    ErrorResponse error = new ErrorResponse("Validacion fallida", detalles);
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(DatabaseError.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Map<String, Object>> handleMethoInternalError(DatabaseError ex) {
    logger.error("Error 500: {}", ex.getMessage());

    Map<String, Object> body =
        Map.of("status", 500, "error", "Internal Server Error", "timestamp", LocalDateTime.now(),
            "message", "Ha ocurrido un error interno. Por favor, contacte con el administrador.");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
