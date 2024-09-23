package ar.edu.um.pcbuilder.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/readyz")
@Slf4j
public class ReadyzCtrl {

  @GetMapping()
  public String readyz() {
    log.info("Readyz endpoint called");
    return "OK";
  }

}
