package com.application.repository;

import com.application.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

    @Query(value = "INSERT INTO user_data.userprofile VALUES(" +
            ":#{#userProfile.id}, " +
            ":#{#userProfile.firstname}, " +
            ":#{#userProfile.lastname}, " +
            ":#{#userProfile.email}, " +
            ":#{#userProfile.phoneNumber}, " +
            ":#{#userProfile.user.id})", nativeQuery = true)
    void createUserProfile(UserProfile userProfile);
}
