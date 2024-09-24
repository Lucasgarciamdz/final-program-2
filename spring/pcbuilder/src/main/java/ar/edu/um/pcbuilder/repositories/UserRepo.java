package ar.edu.um.pcbuilder.repositories;

import ar.edu.um.pcbuilder.dtos.entities.users.UserDto;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepo {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public UserRepo(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public boolean existsByEmail(String email) {
    log.info("Checking if user exists by email: {}", email);
    String sql = "SELECT COUNT(*) FROM Users WHERE email = :email";
    SqlParameterSource params = new MapSqlParameterSource().addValue("email", email);
    Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
    return count != null && count > 0;
  }

  public Optional<UserDto> findByEmail(String email) {
    log.info("Finding user by email: {}", email);
    String sql = "SELECT * FROM Users WHERE email = :email";
    SqlParameterSource params = new MapSqlParameterSource().addValue("email", email);
    try {
      UserDto user = jdbcTemplate.queryForObject(sql, params, userRowMapper());
      return Optional.ofNullable(user);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  private RowMapper<UserDto> userRowMapper() {
    return (rs, rowNum) -> {
      UserDto user = new UserDto();
      user.setId(rs.getLong("id"));
      user.setEmail(rs.getString("email"));
      user.setPassword(rs.getString("password"));
      return user;
    };
  }

  public void save(UserDto user) {
    log.info("Creating user with email: {}", user.getEmail());
    String sql = "INSERT INTO Users (email, password) VALUES (:email, :password)";
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("email", user.getEmail())
        .addValue("password", user.getPassword());
    jdbcTemplate.update(sql, params);
  }
}