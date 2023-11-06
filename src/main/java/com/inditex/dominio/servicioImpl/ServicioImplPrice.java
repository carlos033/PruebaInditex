package com.inditex.dominio.servicioimpl;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.inditex.aplicacion.servicio.ServicioPrice;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.ExcepcionInditex;
import com.inditex.dominio.repositorio.RepositorioPrice;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ServicioImplPrice implements ServicioPrice {
  private static final Logger logger =
      LoggerFactory.getLogger(ServicioImplPrice.class);

  private RepositorioPrice repositorioPrice;

  @Override
  public Price obtenerTarifaAplicar(String idEmpresa, String productId,
      LocalDateTime fechaAConsulta) throws ExcepcionInditex {
    logger.info("Hacemos la llamada al repositorio");

    Price tarifa = repositorioPrice.findPricesWithMaxPriceList(idEmpresa, productId,
        fechaAConsulta);
    if (tarifa == null) {
      logger.info("la tarifa es nula por tanto no existe en la BD");

      throw new ExcepcionInditex(204, "Tarifa no encontrada en la BD");
    }
    return tarifa;
  }
}
