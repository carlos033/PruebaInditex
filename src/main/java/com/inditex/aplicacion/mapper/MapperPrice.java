package com.inditex.aplicacion.mapper;

import java.time.OffsetDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.inditex.aplicacion.dto.PriceDTO;
import com.inditex.infraestructura.entidad.Price;

@Mapper(componentModel = "spring")
public interface MapperPrice {

	@Mapping(target = "consultationDate", source = "fechaAConsultar")
	PriceDTO mapeoADTO(Price entidad, OffsetDateTime fechaAConsultar);

}
