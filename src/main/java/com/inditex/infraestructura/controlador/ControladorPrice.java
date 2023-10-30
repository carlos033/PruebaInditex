package com.inditex.infraestructura.controlador;

import java.time.LocalDateTime;
import java.util.List;
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
  private ServicioPrice servicio;

  private MapperPrice mapper;

  @Operation(
      summary = "Obtener todos los productos por ID de empresa, producto y la fecha de inicio de su precio",
      tags = {"Prices"},
      description = "Obtener todos los productos dado el ID de la empresa, el del producto y la fecha de inicio de su precio")
  @ApiResponse(responseCode = "200", description = "Ok",
      content = @Content(schema = @Schema(implementation = Price.class)))
  @ApiResponse(responseCode = "204", description = "No encontrado",
      content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "500", description = "Bd no accesible",
      content = @Content(schema = @Schema(implementation = Void.class)))
  @GetMapping("/prices/{brandId}/{productId}")
  public ResponseEntity<List<PriceDTO>> listarPrices(
      @PathVariable("brandId") @Parameter(
          description = "  Id de la empresa a la que pertenece",
          required = true) String brandId,
      @PathVariable("productId") @Parameter(
          description = "  Id del producto buscado",
          required = true) String productId,
      @RequestParam("fechaInicio") @Parameter(
          description = " Fecha de inicio del precio") @DateTimeFormat(
              pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaInicio)
      throws ExcepcionInditex {
    logger
        .info("Llamada al servicio, mapeamos la repuesta para mostrar el DTO");

    return new ResponseEntity<>(
        servicio.listarPrices(brandId, productId, fechaInicio).stream()
            .map(mapper::mapeoADTO).toList(),
        HttpStatus.OK);
  }
}
