package ar.edu.um.pcbuilder.services;

import ar.edu.um.pcbuilder.dtos.entities.users.UserDto;
import ar.edu.um.pcbuilder.dtos.requests.LoginDto;
import ar.edu.um.pcbuilder.exceptions.ErrorDetails;
import ar.edu.um.pcbuilder.exceptions.JwtException;
import ar.edu.um.pcbuilder.exceptions.UserException;
import ar.edu.um.pcbuilder.properties.WharehousePpties;
import ar.edu.um.pcbuilder.repositories.UserRepo;
import ar.edu.um.pcbuilder.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserSvc extends BaseSvc {

  private final UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;

  @Autowired
  public UserSvc(WharehousePpties wharehousePpties, UserRepo userRepo,
      PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
    super(wharehousePpties);
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtils = jwtUtils;
  }

  public String signIn(LoginDto loginDto) throws UserException, JwtException {
    UserDto user = userRepo.findByEmail(loginDto.getEmail())
        .orElseThrow(() -> new UserException(null, ErrorDetails.RESOURCE_NOT_FOUND.getMessage(), "User not found"));
    if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
      return jwtUtils.generateToken(user.getEmail());
    } else {
      throw new UserException(null, ErrorDetails.INVALID_REQUEST.getMessage(), "Invalid credentials");
    }
  }

  public String signUp(UserDto userDto) throws UserException {
    if (userRepo.existsByEmail(userDto.getEmail())) {
      throw new UserException(null, ErrorDetails.INVALID_REQUEST.getMessage(), "Email already in use");
    }
    UserDto user = new UserDto();
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    try {
      userRepo.save(user);
      return jwtUtils.generateToken(user.getEmail());
    } catch (Exception e) {
      throw new UserException(e.getMessage(), ErrorDetails.INTERNAL_SERVER_ERROR.getMessage(), "Error during user sign-up");
    }
  }
}