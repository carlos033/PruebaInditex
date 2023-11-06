package com.inditex.dominio.servicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.inditex.dominio.entidad.Price;
import com.inditex.dominio.excepciones.ExcepcionInditex;
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
  void testListarPrices() throws ExcepcionInditex {
    String idEmpresa = "2";
    String idtarifa = "2";
    LocalDateTime fechaInicio = LocalDateTime.now();

    // Crear al menos un tarifa en la lista
    Price tarifa = new Price();
    tarifa.setId(1);
    tarifa.setBrandId(idEmpresa);
    tarifa.setStartDate(fechaInicio);
    tarifa.setEndDate(fechaInicio);
    tarifa.setPriceList("2");
    tarifa.setProductID(idtarifa);
    tarifa.setPriority((short) 1);
    tarifa.setPrecio(22.0);
    tarifa.setCurr("EUR");
   


    when(repositorio.findPricesWithMaxPriceList(idEmpresa, idtarifa,
        fechaInicio)).thenReturn(tarifa);

    Price resultado =
        servicio.obtenerTarifaAplicar(idEmpresa, idtarifa, fechaInicio);

    verify(repositorio).findPricesWithMaxPriceList(idEmpresa, idtarifa,
        fechaInicio);

    assertEquals(tarifa, resultado);
  }

  @Test
  void testListarPrices2() throws ExcepcionInditex {
    String idEmpresa = "2";
    String idtarifa = "2";
    LocalDateTime fechaInicio = LocalDateTime.now();
    Price tarifa = new Price(1, idEmpresa, fechaInicio, fechaInicio, "2",
        idtarifa, (short) 1, 22.0, "EUR");
   

    when(repositorio.findPricesWithMaxPriceList(idEmpresa, idtarifa,
        fechaInicio)).thenReturn(tarifa);

    Price resultado =
        servicio.obtenerTarifaAplicar(idEmpresa, idtarifa, fechaInicio);

    verify(repositorio).findPricesWithMaxPriceList(idEmpresa, idtarifa,
        fechaInicio);

    assertEquals(tarifa, resultado);
  }

  @Test
  void testListarPricesNoEncontrado() {
    String idEmpresa = "empresaId";
    String idtarifa = "tarifaId";
    LocalDateTime fechaInicio = LocalDateTime.now();

    assertThrows(ExcepcionInditex.class,
        () -> servicio.obtenerTarifaAplicar(idEmpresa, idtarifa, fechaInicio));
  }
}
