package com.application.dto;

import com.application.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    String login;
    Boolean enabled;
    Role role;

    String firstname;
    String lastname;
    String email;
    String phoneNumber;

}
