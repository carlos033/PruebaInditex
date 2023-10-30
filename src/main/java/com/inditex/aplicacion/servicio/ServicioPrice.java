package com.inditex.aplicacion.servicio;

import java.time.LocalDateTime;
import java.util.List;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.ExcepcionInditex;

public interface ServicioPrice {

  List<Price> listarPrices(String idEmpresa, String productId,
      LocalDateTime fechaInicio) throws ExcepcionInditex;

}
