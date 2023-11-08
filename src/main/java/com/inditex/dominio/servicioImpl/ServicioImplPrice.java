package com.inditex.dominio.servicioimpl;

import java.time.OffsetDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.inditex.aplicacion.servicio.ServicioPrice;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.NotContentInditex;
import com.inditex.dominio.repositorio.RepositorioPrice;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ServicioImplPrice implements ServicioPrice {
  private static final Logger logger =
      LoggerFactory.getLogger(ServicioImplPrice.class);

  private RepositorioPrice repositorioPrice;

  @Override
  public Price obtenerTarifaAplicar(String idEmpresa,
      String productId, OffsetDateTime fechaAConsulta) throws NotContentInditex {
    logger.info("Hacemos la llamada al repositorio");

    return repositorioPrice
        .findPricesWithMaxPriceList(idEmpresa, productId, fechaAConsulta)
        .orElseThrow(
            () -> new NotContentInditex(204, "Tarifa no encontrada en la BD"));
  }
}
