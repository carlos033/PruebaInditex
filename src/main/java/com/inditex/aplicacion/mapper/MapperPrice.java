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
  @Mapping(target = "startDate", source = "startDate",
      qualifiedByName = "localDateTimeToOffsetDateTime")
  @Mapping(target = "endDate", source = "endDate",
      qualifiedByName = "localDateTimeToOffsetDateTime")
  PriceDTO mapeoADTO(Price entidad);

  @Named("localDateTimeToOffsetDateTime")
  default OffsetDateTime localDateTimeToOffsetDateTime(
      LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }
    return localDateTime.atOffset(ZoneOffset.UTC);
  }
}
