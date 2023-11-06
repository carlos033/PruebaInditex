package com.inditex.aplicacion.mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.inditex.aplicacion.dto.PriceDTO;
import com.inditex.dominio.entidad.Price;

@Mapper(componentModel = "spring")
public interface MapperPrice {

  @Mapping(target = "consultationDate", source = "fechaAConsultar",
      qualifiedByName = "mapLocalDateTimeToOffsetDateTime")
  PriceDTO mapeoADTO(Price entidad, LocalDateTime fechaAConsultar);

  @Named("mapLocalDateTimeToOffsetDateTime")
  default OffsetDateTime mapLocalDateTimeToOffsetDateTime(
      LocalDateTime localDateTime) {
    return localDateTime.atZone(ZoneOffset.UTC).toOffsetDateTime();
  }
}
