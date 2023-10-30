package com.inditex.dominio.servicioImpl;

import java.time.LocalDateTime;
import java.util.List;
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

  private RepositorioPrice repositorio;

  @Override
  public List<Price> listarPrices(String idEmpresa, String productId,
      LocalDateTime fechaInicio) throws ExcepcionInditex {
    logger.info("Hacemos la llamada al repositorio");

    List<Price> lista = repositorio.findByBrandIdAndProductIDAndStartDate(
        idEmpresa, productId, fechaInicio);
    if (lista.isEmpty()) {
      logger.info("la lista viene vacia");

      throw new ExcepcionInditex(204, "El articulo no esta en la BD");
    }
    return lista;
  }
}
