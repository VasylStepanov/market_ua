package com.application.repository;

import com.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT login, password FROM user_data.users " +
            "INNER JOIN user_data.userprofile ON user_data.users.id = user_data.userprofile.user_id" +
            "WHERE user_data.userprofile.email = :email", nativeQuery = true)
    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT login, password FROM user_data.users " +
            "INNER JOIN user_data.userprofile ON user_data.users.id = user_data.userprofile.user_id" +
            "WHERE user_data.userprofile.phone_number = :phoneNumber", nativeQuery = true)
    Optional<User> findUserByPhoneNumber(String phoneNumber);

    @Query(value = "INSERT INTO user_data.users VALUES(" +
            ":#{#user.id}, " +
            ":#{#user.login}, " +
            ":#{#user.password}, " +
            ":#{#user.role}, " +
            ":#{#user.enabled})", nativeQuery = true)
    void createUser(@Param("user") User user);
}
