package ar.edu.um.pcbuilder.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepo {

  NamedParameterJdbcTemplate jdbcTemplate;

  public UserRepo(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

}
