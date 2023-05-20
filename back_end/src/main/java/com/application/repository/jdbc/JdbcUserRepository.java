package com.application.repository.jdbc;

import com.application.dto.UserDTO;
import com.application.entity.User;
import com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Optional<UserDTO> findUserByLogin(String login) {
        System.out.println("Get");
        return template.queryForObject("SELECT login, email, phone_number FROM user_data.users " +
                "INNER JOIN user_data.userprofile ON user_data.users.id = user_data.userprofile.user_id " +
                "WHERE user_data.users.login = :login",
                new MapSqlParameterSource("login", login),
                (rs, rowNum) -> Optional.of(
                        UserDTO.builder()
                                .login(rs.getString("login"))
                                .email(rs.getString("email"))
                                .phoneNumber(rs.getString("phone_number")).build()));
    }

    @Override
    public void createUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
        params.put("role", user.getRole().name());
        params.put("enabled", user.getEnabled());

        System.out.println(user);
        template.update(
                "INSERT INTO user_data.users VALUES(:id, :login, :password, :role, :enabled);",
                params);
    }
}
