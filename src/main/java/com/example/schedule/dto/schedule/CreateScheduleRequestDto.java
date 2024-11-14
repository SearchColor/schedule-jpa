package com.example.schedule.dto.schedule;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String username;

    private final String title;

    private final String detail;


    public CreateScheduleRequestDto(String username, String title, String detail) {
        this.username = username;
        this.title = title;
        this.detail = detail;
    }
}
