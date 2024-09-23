package ar.edu.um.pcbuilder.services;

import ar.edu.um.pcbuilder.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserSvc extends BaseSvc {

  UserRepo userRepo;

  public UserSvc(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

}
