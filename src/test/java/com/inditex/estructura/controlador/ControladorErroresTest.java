package com.inditex.estructura.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.inditex.dominio.excepciones.ErrorResponse;
import com.inditex.dominio.excepciones.NotContentInditex;
import com.inditex.infraestructura.controlador.ControladorErrores;

@ExtendWith(MockitoExtension.class)
class ControladorErroresTest {

  @InjectMocks
  private ControladorErrores controladorErrores;

  @Mock
  private MethodArgumentNotValidException methodArgumentNotValidException;

  @Test
  void testItemNotFoundHandler() {
    // Crear una instancia de NotContentInditex
    NotContentInditex exception = new NotContentInditex(204, "Not content");

    // Obtener el mensaje de error de la excepción y verificar que
    // coincida con la cadena de error
    String mensajeDeError = exception.getMessage();
    assertEquals("Not content", mensajeDeError);
  }



  @Test
  void testHandleMethodArgumentNotValid() {
    // Crear una instancia de MethodArgumentNotValidException simulada
    MethodArgumentNotValidException methodArgumentNotValidException =
        mock(MethodArgumentNotValidException.class);

    // Crear un objeto BindingResult simulado y configurar su
    // comportamiento
    BindingResult bindingResult = mock(BindingResult.class);
    List<ObjectError> errors = new ArrayList<>();
    errors.add(new FieldError("objectName", "fieldName", "Error message 1"));
    errors.add(new FieldError("objectName", "fieldName", "Error message 2"));
    when(bindingResult.getAllErrors()).thenReturn(errors);

    // Configurar el comportamiento de getBindingResult() para devolver el
    // BindingResult simulado
    when(methodArgumentNotValidException.getBindingResult())
        .thenReturn(bindingResult);

    // Llamar al método handleMethodArgumentNotValid
    ResponseEntity<Object> result = controladorErrores
        .handleMethodArgumentNotValid(methodArgumentNotValidException);

    // Verificar que el resultado es un ResponseEntity con el mensaje de
    // error y HttpStatus.BAD_REQUEST
    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    ErrorResponse errorResponse = (ErrorResponse) result.getBody();
    assertEquals("Validacion fallida", errorResponse.getMensaje());
    assertEquals(2, errorResponse.getDetalles().size());
  }

  @Test
  void testHandleMethoInternalError() {
    // Crear una instancia de MethodArgumentNotValidException simulada
    MethodArgumentNotValidException methodArgumentNotValidException =
        mock(MethodArgumentNotValidException.class);

    // Crear un objeto BindingResult simulado y configurar su
    // comportamiento
    BindingResult bindingResult = mock(BindingResult.class);
    List<ObjectError> errors = new ArrayList<>();
    errors.add(new ObjectError("objName", "Error 1"));
    errors.add(new ObjectError("objName", "Error 2"));

    // Configurar el comportamiento de getBindingResult() para devolver el
    // BindingResult simulado
    when(methodArgumentNotValidException.getBindingResult())
        .thenReturn(bindingResult);

    // Configurar el comportamiento de getAllErrors() en el objeto
    // BindingResult simulado
    when(bindingResult.getAllErrors()).thenReturn(errors);

    // Llamar al método handleMethoInternalError
    ResponseEntity<Object> result = controladorErrores
        .handleMethoInternalError(methodArgumentNotValidException);

    // Verificar que el resultado es un ResponseEntity con el mensaje de
    // error y HttpStatus.INTERNAL_SERVER_ERROR
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    ErrorResponse errorResponse = (ErrorResponse) result.getBody();
    assertEquals(errors.size(), errorResponse.getDetalles().size());
  }
}
