package ar.edu.um.pcbuilder.dtos.entities.users;

import lombok.Data;

@Data
public class UserDto {

  private Long id;
  private String email;
  private String password;

  public UserDto() {
    this.id = null;
    this.email = null;
    this.password = null;
  }

  public UserDto(String email, String password) {
    this.id = null;
    this.email = email;
    this.password = password;
  }

  public UserDto(Long id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public UserDto(String email) {
    this.id = null;
    this.email = email;
    this.password = null;
  }
}
