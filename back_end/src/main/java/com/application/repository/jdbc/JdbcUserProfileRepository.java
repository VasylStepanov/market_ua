package com.application.repository.jdbc;

import com.application.entity.UserProfile;
import com.application.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcUserProfileRepository implements UserProfileRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void createUserProfile(UserProfile userProfile) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", userProfile.getId());
        params.put("firstname", userProfile.getFirstname());
        params.put("lastname", userProfile.getLastname());
        params.put("email", userProfile.getEmail());
        params.put("phoneNumber", userProfile.getPhoneNumber());
        params.put("userId", userProfile.getUser().getId());

        template.update(
                "INSERT INTO user_data.userprofile VALUES(:id, :firstname, :lastname, :email, :phoneNumber, :userId)",
                params);
    }
}
