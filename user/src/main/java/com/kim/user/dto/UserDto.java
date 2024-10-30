package com.kim.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "User",
        description = "Schema to hold User information"
)
public class UserDto {
    @Schema(
            description = "Name of the user", example = "Rono Kim"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the user name should be between 5 and 30")
    private String userName;

    @Schema(
            description = "Email address of the user", example = "kim@gmail.com"
    )
    @NotEmpty
    @Email(message = "Email address should be a valid value")
    private String userEmail;

    @Schema(
            description = "Mobile Number of the user", example = "9345432123"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Password of the user", example = "9345432123"
    )
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long and include at least one digit, one letter, and one special character")
    private String password;
}
