package ar.edu.um.pcbuilder.controllers;

import ar.edu.um.pcbuilder.dtos.entities.users.UserDto;
import ar.edu.um.pcbuilder.dtos.requests.LoginDto;
import ar.edu.um.pcbuilder.dtos.responses.BaseResponse;
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
  public BaseResponse<UserDto> signIn(@RequestBody LoginDto loginDto) {
    log.info("User sign-in attempt with username: {}", loginDto.getEmail());
    try {
      UserDto userDto = userSvc.signIn(loginDto);
      log.info("User signed in successfully: {}", userDto.getEmail());
      return new BaseResponse<>("User signed in successfully", userDto);
    } catch (Exception e) {
      log.error("Error during user sign-in", e);
      return new BaseResponse<>("Error during user sign-in", null);
    }
  }

  @PostMapping("/sign-up")
  public BaseResponse<UserDto> signUp(@RequestBody UserDto userDto) {
    log.info("User sign-up attempt with username: {}", userDto.getEmail());
    try {
      UserDto createdUser = userSvc.signUp(userDto);
      log.info("User signed up successfully: {}", createdUser.getEmail());
      return new BaseResponse<>("User signed up successfully", createdUser);
    } catch (Exception e) {
      log.error("Error during user sign-up", e);
      return new BaseResponse<>("Error during user sign-up", null);
    }
  }
}