package com.inditex.aplicacion.servicio;

import java.time.LocalDateTime;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.ExcepcionInditex;

public interface ServicioPrice {

  Price obtenerTarifaAplicar(String idEmpresa, String productId,
      LocalDateTime fechaInicio) throws ExcepcionInditex;

}
