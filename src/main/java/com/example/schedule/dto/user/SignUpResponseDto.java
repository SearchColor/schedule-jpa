package com.example.schedule.dto.user;


import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    private final String password;

    public SignUpResponseDto(Long id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
