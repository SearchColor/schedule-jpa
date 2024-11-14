package com.example.schedule.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class CreateScheduleRequestDto {

    @NotBlank(message = "username 은 필수값 입니다.")
    @Size(min = 1, max = 5, message = "username 은 1~5 글자여야 합니다.")
    private final String username;

    @NotBlank(message = "username 은 필수값 입니다.")
    @Size(min = 1, max = 10, message = "title 은 10글자 이내여야 합니다.")
    private final String title;

    @NotBlank(message = "username 은 필수값 입니다.")
    @Size(min = 1, max = 20, message = "detail 은 20글자 이내여야 합니다.")
    private final String detail;


    public CreateScheduleRequestDto(String username, String title, String detail) {
        this.username = username;
        this.title = title;
        this.detail = detail;
    }
}
