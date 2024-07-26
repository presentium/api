package ch.presentium.backend;

import org.springframework.boot.SpringApplication;

public class TestPresentiumApiApplication {

  public static void main(String[] args) {
    SpringApplication.from(PresentiumApiApplication::main)
        .with(TestcontainersConfiguration.class)
        .run(args);
  }
}
