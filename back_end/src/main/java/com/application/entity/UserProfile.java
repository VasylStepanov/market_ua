package com.application.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userprofile", schema = "user_data")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfile {

    @Id
    UUID id;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}