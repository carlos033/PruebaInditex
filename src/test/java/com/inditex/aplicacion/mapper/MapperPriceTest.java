package com.inditex.aplicacion.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import com.inditex.aplicacion.dto.PriceDTO;
import com.inditex.dominio.entidad.Price;

@ExtendWith(MockitoExtension.class)
class MapperPriceTest {
  private MapperPrice mapper = Mappers.getMapper(MapperPrice.class);

  @Test
  void testMapeoADTO() {
    Price price = new Price();
    price.setStartDate(LocalDateTime.of(2023, 10, 30, 12, 0));
    price.setEndDate(LocalDateTime.of(2023, 11, 30, 12, 0));

    PriceDTO priceDTO = mapper.mapeoADTO(price);

    assertEquals(OffsetDateTime.of(2023, 10, 30, 12, 0, 0, 0, ZoneOffset.UTC),
        priceDTO.getStartDate());
    assertEquals(OffsetDateTime.of(2023, 11, 30, 12, 0, 0, 0, ZoneOffset.UTC),
        priceDTO.getEndDate());
  }

  @Test
  void testLocalDateTimeToOffsetDateTime() {
    LocalDateTime localDateTime = LocalDateTime.of(2023, 10, 30, 12, 0);
    OffsetDateTime offsetDateTime =
        mapper.localDateTimeToOffsetDateTime(localDateTime);

    assertEquals(OffsetDateTime.of(2023, 10, 30, 12, 0, 0, 0, ZoneOffset.UTC),
        offsetDateTime);
  }

  @Test
  void testLocalDateTimeToOffsetDateTimeWithNull() {
    LocalDateTime localDateTime = null;

    OffsetDateTime offsetDateTime =
        mapper.localDateTimeToOffsetDateTime(localDateTime);

    assertNull(offsetDateTime);
  }
}
