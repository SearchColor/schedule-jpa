package com.example.schedule.dto.schedule;


import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    private final String password;

    private final String title;

    private final String detail;

    public UpdateScheduleRequestDto(String password, String title, String detail) {
        this.password = password;
        this.title = title;
        this.detail = detail;
    }
}
