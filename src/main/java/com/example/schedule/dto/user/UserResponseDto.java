package com.example.schedule.dto.user;


import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String username;

    private final String email;

    private final String password;


    public UserResponseDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
