package com.inditex.dominio.servicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.NotContentInditex;
import com.inditex.dominio.repositorio.RepositorioPrice;
import com.inditex.dominio.servicioimpl.ServicioImplPrice;

@ExtendWith(MockitoExtension.class)
class ServicioImplTest {

  @InjectMocks
  private ServicioImplPrice servicio;

  @Mock
  private RepositorioPrice repositorio;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testListarPrices() throws NotContentInditex {
    String idEmpresa = "2";
    String idtarifa = "2";
    OffsetDateTime fechaInicio = OffsetDateTime.now();

    // Crear al menos un tarifa en la lista
    Price tarifa = new Price();
    tarifa.setBrandId(idEmpresa);
    tarifa.setStartDate(fechaInicio);
    tarifa.setEndDate(fechaInicio);
    tarifa.setPriceList(2);
    tarifa.setProductID(idtarifa);
    tarifa.setPriority((short) 1);
    tarifa.setPrecio(22.0);
    tarifa.setCurr("EUR");
    Optional<Price> optTarifa = Optional.of(tarifa);


    when(repositorio.obtenerTarifaAplicar(idEmpresa, idtarifa, fechaInicio))
        .thenReturn(optTarifa);

    Price resultado =
        servicio.obtenerTarifaAplicar(idEmpresa, idtarifa, fechaInicio);

    verify(repositorio).obtenerTarifaAplicar(idEmpresa, idtarifa, fechaInicio);

    assertEquals(tarifa, resultado);
  }

  @Test
  void testListarPrices2() throws NotContentInditex {
    String idEmpresa = "2";
    String idtarifa = "2";
    OffsetDateTime fecha = OffsetDateTime.now();
    Price tarifa =
        new Price(2, idEmpresa, fecha, fecha, idtarifa, (short) 1, 22.0, "EUR");
    Optional<Price> optTarifa = Optional.of(tarifa);


    when(repositorio.obtenerTarifaAplicar(idEmpresa, idtarifa, fecha))
        .thenReturn(optTarifa);

    Price resultado = servicio.obtenerTarifaAplicar(idEmpresa, idtarifa, fecha);

    verify(repositorio).obtenerTarifaAplicar(idEmpresa, idtarifa, fecha);

    assertEquals(tarifa, resultado);
  }

  @Test
  void testListarPricesNoEncontrado() {
    String idEmpresa = "empresaId";
    String idtarifa = "tarifaId";
    OffsetDateTime fechaInicio = OffsetDateTime.now();

    assertThrows(NotContentInditex.class,
        () -> servicio.obtenerTarifaAplicar(idEmpresa, idtarifa, fechaInicio));
  }
}

