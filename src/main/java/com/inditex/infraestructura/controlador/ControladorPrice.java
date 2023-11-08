package com.inditex.infraestructura.controlador;

import java.time.OffsetDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.inditex.aplicacion.api.PricesApi;
import com.inditex.aplicacion.dto.PriceDTO;
import com.inditex.aplicacion.mapper.MapperPrice;
import com.inditex.aplicacion.servicio.ServicioPrice;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ControladorPrice implements PricesApi {

  private static final Logger logger =
      LoggerFactory.getLogger(ControladorPrice.class);
  private ServicioPrice servicioBusquedaTarifa;

  private MapperPrice mapper;

  @Override
  public ResponseEntity<PriceDTO> obtenerTarifaAplicar(String brandId,
      String productId, OffsetDateTime fechaAConsultar) {
    logger
        .info("Llamada al servicio, mapeamos la repuesta para mostrar el DTO");
    return ResponseEntity.ok(
        mapper.mapeoADTO(servicioBusquedaTarifa.obtenerTarifaAplicar(brandId,
            productId, fechaAConsultar), fechaAConsultar));
  }

}
