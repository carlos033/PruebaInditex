package com.inditex.aplicacion.servicio;

import java.time.OffsetDateTime;
import com.inditex.dominio.excepciones.NotContentInditex;
import com.inditex.infraestructura.entidad.Price;

public interface ServicioPrice {

	Price obtenerTarifaAplicar(String idEmpresa, String productId, OffsetDateTime fechaInicio) throws NotContentInditex;

}
