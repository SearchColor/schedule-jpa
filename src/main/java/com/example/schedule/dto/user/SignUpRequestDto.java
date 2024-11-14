package com.example.schedule.dto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;



@Getter
public class SignUpRequestDto {

    @NotBlank(message = "username 은 필수값 입니다.")
    @Size(min = 1, max = 5, message = "username 은 1~5 글자여야 합니다.")
    private final String username;


    @NotBlank(message = "email 은 필수값 입니다.")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message = "password 는 필수값 입니다.")
    @Size(min = 1,max = 10, message = "password 은 1~10 글자여야 합니다.")
    private final String password;


    public SignUpRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }
}
