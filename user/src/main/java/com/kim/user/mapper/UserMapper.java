package com.kim.user.mapper;

import com.kim.user.dto.UserDto;
import com.kim.user.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user, UserDto userDto) {
        userDto.setUserName(user.getUserName());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setPassword(user.getPassword());
        userDto.setMobileNumber(user.getMobileNumber());
        return userDto;
    }
    public static User mapToUser(UserDto userDto,User user){
        user.setUserName(userDto.getUserName());
        user.setUserEmail(userDto.getUserEmail());
        user.setPassword(userDto.getPassword());
        user.setMobileNumber(userDto.getMobileNumber());
        return user;
    }

}
