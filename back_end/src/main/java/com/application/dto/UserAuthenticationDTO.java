package com.application.dto;

import com.application.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthenticationDTO {

    UUID userId;
    Role role;
    Boolean enabled;
    String login;
}
