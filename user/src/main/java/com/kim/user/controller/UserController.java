package com.kim.user.controller;

import com.kim.user.contants.UserConstants;
import com.kim.user.dto.ResponseDto;
import com.kim.user.dto.UserDto;
import com.kim.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserDto userDto){
        userService.signup(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201,UserConstants.MESSAGE_201));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> signin(@RequestBody String userName, String password ) {
        return ResponseEntity
                .status(HttpStatus.PROCESSING)
                .body(new ResponseDto(UserConstants.STATUS_201,UserConstants.MESSAGE_201));
    }

//    private String generateToken(String username) {
//        // Implement logic to generate a secure token based on user details (not username only)
//        return "sample-token-" + username;
//    }

}
