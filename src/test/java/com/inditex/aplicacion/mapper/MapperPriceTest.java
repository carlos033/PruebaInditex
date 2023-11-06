package com.inditex.aplicacion.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
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
    price.setStartDate(LocalDateTime.of(2023, 10, 30, 12, 0));
    price.setEndDate(LocalDateTime.of(2023, 11, 30, 12, 0));
    price.setId(1);
    price.setPrecio(39.12);
    price.setPriceList("1");
    price.setPriority((short) 1);
    price.setProductID("3915");
    LocalDateTime fechaConsulta = LocalDateTime.now();
    PriceDTO priceDTO = mapper.mapeoADTO(price, fechaConsulta);
    assertEquals(priceDTO.getBrandId(), price.getBrandId());
    assertEquals(priceDTO.getPriceList(), price.getPriceList());
    assertEquals(priceDTO.getPriority(), price.getPriority());
    assertEquals(priceDTO.getProductID(), price.getProductID());
  }
}
