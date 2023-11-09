package com.inditex.dominio.excepciones;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {

  private String mensaje;
  private List<String> detalles;

}
