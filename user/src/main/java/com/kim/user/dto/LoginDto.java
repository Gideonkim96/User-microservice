package com.kim.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class LoginDto {
    private String userName;
    private String password;
}
