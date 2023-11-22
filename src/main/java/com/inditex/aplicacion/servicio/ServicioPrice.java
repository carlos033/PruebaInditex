package com.inditex.aplicacion.servicio;

import java.time.OffsetDateTime;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.NotContentInditex;

public interface ServicioPrice {

  Price obtenerTarifaAplicar(String idEmpresa, String productId, OffsetDateTime fechaInicio)
      throws NotContentInditex;

}
