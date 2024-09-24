package ar.edu.um.pcbuilder.dtos.requests;

import lombok.Data;

@Data
public class LoginDto {

  private String email;
  private String password;

}
