package com.inditex.aplicacion.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import com.inditex.aplicacion.dto.PriceDTO;
import com.inditex.presentacion.api.PricesApi;

@ExtendWith(MockitoExtension.class)
class PricesApiTest {
	private final PricesApi pricesApi = new PricesApi() {
		@Override
		public ResponseEntity<PriceDTO> obtenerTarifaAplicar(String brandId, String productId,
				OffsetDateTime fechaAConsultar) {
			return ResponseEntity.ok(new PriceDTO());
		}
	};

	@Test
	void getRequestReturnsEmptyOptional() {
		Optional<NativeWebRequest> request = pricesApi.getRequest();
		assertTrue(request.isEmpty());
	}
}
