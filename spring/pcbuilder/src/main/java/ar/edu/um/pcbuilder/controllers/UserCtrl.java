package ar.edu.um.pcbuilder.controllers;

import ar.edu.um.pcbuilder.dtos.entities.users.UserDto;
import ar.edu.um.pcbuilder.dtos.requests.LoginDto;
import ar.edu.um.pcbuilder.dtos.responses.BaseResponse;
import ar.edu.um.pcbuilder.exceptions.JwtException;
import ar.edu.um.pcbuilder.exceptions.UserException;
import ar.edu.um.pcbuilder.services.UserSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserCtrl {

  private final UserSvc userSvc;

  public UserCtrl(UserSvc userSvc) {
    this.userSvc = userSvc;
  }

  @PostMapping("/sign-in")
  public BaseResponse<String> signIn(@RequestBody LoginDto loginDto) throws UserException, JwtException {
    log.info("User sign-in attempt with username: {}", loginDto.getEmail());
    String token = userSvc.signIn(loginDto);
    log.info("User signed in successfully: {}", loginDto.getEmail());
    return new BaseResponse<>("User signed in successfully", token);

  }

  @PostMapping("/sign-up")
  public BaseResponse<String> signUp(@RequestBody UserDto userDto) throws UserException {
    log.info("User sign-up attempt with username: {}", userDto.getEmail());
    String token = userSvc.signUp(userDto);
    log.info("User signed up successfully: {}", userDto.getEmail());
    return new BaseResponse<>("User signed up successfully", token);
  }
  }