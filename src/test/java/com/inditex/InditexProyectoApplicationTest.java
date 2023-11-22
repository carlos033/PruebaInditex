package com.inditex;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InditexProyectoApplicationTest {

  @Mock
  private ApplicationContext applicationContext;

  @Test
  void contextLoads() {
    assertNotNull(applicationContext, "El contexto de la aplicación no debería ser nulo");
  }

  @Test
  void test() {
    InditexProyectoApplication.main(new String[] {"--spring.main.web-environment=false",
        "--spring.autoconfigure.exclude=blahblahblah",});
  }
}
