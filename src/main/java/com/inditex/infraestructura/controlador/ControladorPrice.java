package com.inditex.infraestructura.controlador;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.inditex.aplicacion.api.PricesApi;
import com.inditex.aplicacion.dto.PriceDTO;
import com.inditex.aplicacion.mapper.MapperPrice;
import com.inditex.aplicacion.servicio.ServicioPrice;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.ExcepcionInditex;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ControladorPrice implements PricesApi {

  private static final Logger logger =
      LoggerFactory.getLogger(ControladorPrice.class);
  private ServicioPrice servicioBusquedaTarifa;

  private MapperPrice mapper;

  @Operation(
      summary = "Obtener la tarifa a aplicar teniendo en cuenta el ID de la empresa del grupo, el id del producto y la fecha que nos interesa",
      tags = {"Prices"},
      description = "Obtener la tarifa a aplicar teniendo en cuenta el ID de la empresa del grupo, el id del producto y la fecha que nos interesa")
  @ApiResponse(responseCode = "200", description = "Ok",
      content = @Content(schema = @Schema(implementation = Price.class)))
  @ApiResponse(responseCode = "204", description = "No encontrado",
      content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "500", description = "Bd no accesible",
      content = @Content(schema = @Schema(implementation = Void.class)))
  @GetMapping("/prices/{brandId}/{productId}")
  public ResponseEntity<PriceDTO> obtenerTarifaAplicar(
      @PathVariable("brandId") @Parameter(
          description = "  Id de la empresa del grupo",
          required = true) String brandId,
      @PathVariable("productId") @Parameter(
          description = "  Id del producto buscado",
          required = true) String productId,
      @RequestParam("fechaAConsultar") @Parameter(
          description = " Fecha de consulta para la tarifa") @DateTimeFormat(
              pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaAConsultar)
      throws ExcepcionInditex {
    logger
        .info("Llamada al servicio, mapeamos la repuesta para mostrar el DTO");
    return new ResponseEntity<>(mapper.mapeoADTO(
        servicioBusquedaTarifa.obtenerTarifaAplicar(brandId, productId, fechaAConsultar),
        fechaAConsultar), HttpStatus.OK);
  }
}
