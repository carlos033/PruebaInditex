package com.inditex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InditexProyectoApplicationTest {
  @Test
  void contextLoads() {}

  @Test
  void test() {
    InditexProyectoApplication.main(new String[] {"--spring.main.web-environment=false",
        "--spring.autoconfigure.exclude=blahblahblah",});
  }
}
