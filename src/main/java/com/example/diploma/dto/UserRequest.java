package com.example.diploma.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull(message = "Invalid Username: username is null")
    @NotBlank
    private String username;

    @NotNull(message = "Invalid Name: name is null")
    @NotBlank
    private String name;

    @NotBlank
    @NotNull(message = "Invalid Last Name: lastName is null")
    private String lastName;

    @Email(message = "Invalid Email")
    private String email;

    @NotBlank
    @NotNull(message = "Invalid Password: password is null")
    @Size.List({
            @Size(min = 4, message = "Password length must be at least 4 characters"),
            @Size(max = 100, message = "Password length must be less than 100 characters")
    })
    private String password;

    @NotNull(message = "Invalid Rating: rating is null")
    private double rating;
}
