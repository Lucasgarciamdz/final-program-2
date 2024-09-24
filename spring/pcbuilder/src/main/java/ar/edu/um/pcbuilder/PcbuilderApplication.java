package ar.edu.um.pcbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PcbuilderApplication {

  public static void main(String[] args) {
    SpringApplication.run(PcbuilderApplication.class, args);
  }

}
