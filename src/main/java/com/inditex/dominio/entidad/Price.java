package com.inditex.dominio.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Price implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull
  @Schema(name = "id", description = "Id de la tarifa", example = "1")
  private int id;

  @NotNull
  @Schema(name = "brand_id",
      description = "Id que representa la empresa del grupo", example = "1")
  @Size(min = 1)
  private String brandId;

  @Schema(name = "startDate",
      description = "Fecha en la que se empieza a aplicar"
          + " el precio tarifa indicado formato yyyy-MM-ddTHH:mm:ss",
      example = "2023-05-01T14:30:00")
  @JsonFormat(shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime startDate;

  @Schema(name = "endDate", description = "fechas en la que se finaliza la"
      + " aplicacion del precio tarifa indicado formato yyyy-MM-ddTHH:mm:ss",
      example = "2023-05-01T14:30:00")
  @JsonFormat(shape = JsonFormat.Shape.STRING,
      pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime endDate;

  @Schema(name = "price_list",
      description = " Identificador de la tarifa de precios " + "aplicable",
      example = "2")
  private String priceList;

  @Column(name = "product_id")
  @Schema(name = "productID", description = "Identificador código de producto",
      example = "35455")
  private String productID;

  @Schema(name = "priority", description = "prioridad del precio",
      example = "1")
  private short priority;

  @Column(name = "price")
  @Schema(name = "precio", description = " Precio del producto",
      example = "21.99")
  private double precio;

  @Schema(name = "curr", description = " Moneda en la que se paga",
      example = "EUR")
  private String curr;
}
