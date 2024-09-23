package ar.edu.um.pcbuilder.controllers;

import ar.edu.um.pcbuilder.services.UserSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserCtrl {


  UserSvc userSvc;

  public UserCtrl(UserSvc userSvc) {
    this.userSvc = userSvc;
  }
}
