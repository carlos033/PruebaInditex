package com.inditex.aplicacion.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
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
    price.setBrandId("1");
    price.setCurr("EUR");
    price.setStartDate(
        OffsetDateTime.of(2023, 10, 30, 12, 0, 0, 0, ZoneOffset.UTC));
    price.setEndDate(
        OffsetDateTime.of(2023, 11, 30, 12, 0, 0, 0, ZoneOffset.UTC));
    price.setPrecio(39.12);
    price.setPriceList(1);
    price.setPriority((short) 1);
    price.getPriority();
    price.setProductID("3915");
    PriceDTO priceDTO = new PriceDTO();
    priceDTO.setBrandId("1");
    priceDTO.setConsultationDate(
        OffsetDateTime.of(2023, 10, 30, 12, 0, 0, 0, ZoneOffset.UTC));
    priceDTO.setCurr("EUR");
    BigDecimal precio = BigDecimal.valueOf(39.12);
    priceDTO.setPrecio(precio);
    priceDTO.setPriceList(1);
    priceDTO.setProductID("3915");
    priceDTO = mapper.mapeoADTO(price, price.getEndDate());
    assertEquals(priceDTO.getBrandId(), price.getBrandId());
    assertEquals(priceDTO.getPriceList(), price.getPriceList());
    assertEquals(priceDTO.getProductID(), price.getProductID());
    assertEquals(priceDTO.getPrecio().doubleValue(), price.getPrecio());
    assertEquals(priceDTO.getConsultationDate(), price.getEndDate());
  }

  @Test
  void testMapeoADTO2() {
    OffsetDateTime fechaConsulta = OffsetDateTime.now();
    BigDecimal precio = BigDecimal.valueOf(39.12);
    Price price = new Price(1, "1", fechaConsulta, fechaConsulta, "3915",
        (short) 1, 39.12, "EUR");
    PriceDTO priceDTO =
        new PriceDTO(1, "1", "3915", fechaConsulta, precio, "EUR");
    priceDTO = mapper.mapeoADTO(price, fechaConsulta);
    assertEquals(priceDTO.getBrandId(), price.getBrandId());
    assertEquals(priceDTO.getPriceList(), price.getPriceList());
    assertEquals(priceDTO.getProductID(), price.getProductID());
    assertEquals(priceDTO.getPrecio().doubleValue(), price.getPrecio());
    assertEquals(priceDTO.getConsultationDate(), price.getStartDate());
  }


}
