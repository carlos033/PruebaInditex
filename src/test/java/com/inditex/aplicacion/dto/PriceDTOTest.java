package com.inditex.aplicacion.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceDTOTest {

	@Mock
	private PriceDTO mockedPriceDTO;

	@Test
	void testPriceDTO() {
		// Set up the mocked values
		String brandId = "1";
		String productId = "1565";
		OffsetDateTime consultationDate = OffsetDateTime.parse("2023-05-01T14:30Z");
		BigDecimal precio = new BigDecimal("19.99");
		String curr = "EUR";

		// Mock the behavior of the getter methods
		when(mockedPriceDTO.getBrandId()).thenReturn(brandId);
		when(mockedPriceDTO.getProductId()).thenReturn(productId);
		when(mockedPriceDTO.getConsultationDate()).thenReturn(consultationDate);
		when(mockedPriceDTO.getPrecio()).thenReturn(precio);
		when(mockedPriceDTO.getCurr()).thenReturn(curr);

		// Test the getter methods
		assertEquals(brandId, mockedPriceDTO.getBrandId());
		assertEquals(productId, mockedPriceDTO.getProductId());
		assertEquals(consultationDate, mockedPriceDTO.getConsultationDate());
		assertEquals(precio, mockedPriceDTO.getPrecio());
		assertEquals(curr, mockedPriceDTO.getCurr());
	}
}
