package com.kim.user.service;

import com.kim.user.dto.LoginDto;
import com.kim.user.dto.UserDto;
import com.kim.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void signup(UserDto userDto);
    User signin(String userName, String password);

    UserDetails loadUserByUsername(String s);
}
