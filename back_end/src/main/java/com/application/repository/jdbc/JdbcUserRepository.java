package com.application.repository.jdbc;

import com.application.dto.UserAuthenticationDTO;
import com.application.dto.UserDTO;
import com.application.entity.Role;
import com.application.entity.User;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<User> findUserById(UUID id) {
        return template.queryForObject("SELECT * FROM user_data.users WHERE id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) -> Optional.of(
                        User.builder()
                                .id((UUID) rs.getObject("id"))
                                .login(rs.getString("login"))
                                .password(rs.getString("password"))
                                .role(Role.valueOf(rs.getString("role")))
                                .enabled(rs.getBoolean("enabled"))
                                .build()
                ));
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return template.queryForObject("SELECT * FROM user_data.users WHERE login = :login",
                new MapSqlParameterSource("login", login),
                (rs, rowNum) -> Optional.of(
                        User.builder()
                                .id((UUID) rs.getObject("id"))
                                .login(rs.getString("login"))
                                .password(rs.getString("password"))
                                .role(Role.valueOf(rs.getString("role")))
                                .enabled(rs.getBoolean("enabled"))
                                .build()
                ));    }

    @Override
    public Optional<UserDTO> findUserDTOByLogin(String login) {
        return findUserByData("users.login", login);
    }

    @Override
    public Optional<UserDTO> findUserDTOByEmail(String email) {
        return findUserByData("userprofile.email", email);
    }

    @Override
    public Optional<UserDTO> findUserDTOByPhoneNumber(String phoneNumber) {
        return findUserByData("userprofile.phone_number", phoneNumber);
    }

    @Override
    public Optional<UserAuthenticationDTO> findAuthUserByEmail(String email) {
        return findAuthUserByData("email", email);
    }

    @Override
    public Optional<UserAuthenticationDTO> findAuthUserByPhoneNumber(String phoneNumber) {
        return findAuthUserByData("phone_number", phoneNumber);
    }

    private Optional<UserDTO> findUserByData(String getFrom, String data){
        return template.queryForObject(String.format("SELECT login, role, enabled, firstname, lastname, email, phone_number FROM user_data.users " +
                        "INNER JOIN user_data.userprofile ON user_data.users.id = user_data.userprofile.user_id " +
                        "WHERE user_data.%s = :data", getFrom),
                new MapSqlParameterSource("data", data),
                (rs, rowNum) -> Optional.of(
                        UserDTO.builder()
                                .login(rs.getString("login"))
                                .role(Role.valueOf(rs.getString("role")))
                                .enabled(rs.getBoolean("enabled"))
                                .firstname(rs.getString("firstname"))
                                .lastname(rs.getString("lastname"))
                                .email(rs.getString("email"))
                                .phoneNumber(rs.getString("phone_number")).build()));
    }

    private Optional<UserAuthenticationDTO> findAuthUserByData(String getData, String data){
        return template.queryForObject(String.format("SELECT users.id, role, enabled, login, password FROM user_data.users " +
                        "INNER JOIN user_data.userprofile ON user_data.users.id = user_data.userprofile.user_id " +
                        "WHERE user_data.userprofile.%s = :data", getData),
                new MapSqlParameterSource("data", data),
                (rs, rowNum) -> Optional.of(
                        UserAuthenticationDTO.builder()
                                .userId((UUID)rs.getObject("id"))
                                .role(Role.valueOf(rs.getString("role")))
                                .enabled(rs.getBoolean("enabled"))
                                .login(rs.getString("login")).build()));
    }

    @Override
    public void createUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
        params.put("role", user.getRole().name());
        params.put("enabled", user.getEnabled());

        template.update(
                "INSERT INTO user_data.users VALUES(:id, :login, :password, :role, :enabled);",
                params);
    }
}
