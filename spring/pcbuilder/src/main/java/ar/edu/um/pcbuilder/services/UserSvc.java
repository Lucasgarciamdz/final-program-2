package ar.edu.um.pcbuilder.services;

import ar.edu.um.pcbuilder.dtos.entities.users.UserDto;
import ar.edu.um.pcbuilder.dtos.requests.LoginDto;
import ar.edu.um.pcbuilder.properties.WharehousePpties;
import ar.edu.um.pcbuilder.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserSvc extends BaseSvc {

  private final UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserSvc(WharehousePpties wharehousePpties, UserRepo userRepo,
      PasswordEncoder passwordEncoder) {
    super(wharehousePpties);
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto signIn(LoginDto loginDto) throws Exception {
    UserDto user = userRepo.findByEmail(loginDto.getEmail())
        .orElseThrow(() -> new Exception("User not found"));
    if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
      return new UserDto(user.getEmail());
    } else {
      throw new Exception("Invalid credentials");
    }
  }

  public UserDto signUp(UserDto userDto) throws Exception {
    if (userRepo.existsByEmail(userDto.getEmail())) {
      throw new Exception("Email already in use");
    }
    UserDto user = new UserDto();
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    try {

      userRepo.save(user);
      return user;
    } catch (Exception e) {
      throw new Exception("Error during user sign-up");
    }
  }
}