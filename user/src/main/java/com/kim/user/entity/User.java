package com.kim.user.entity;


import jakarta.persistence.*;
import lombok.*;

import java.security.SecureRandom;

import static com.kim.user.contants.UserConstants.SALT_LENGTH;

@Entity @Getter @Setter @ToString @AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String mobileNumber;

    private String userEmail;

    private String password;


    public void setSalt(String encodeToString) {
    }

    public byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
}
