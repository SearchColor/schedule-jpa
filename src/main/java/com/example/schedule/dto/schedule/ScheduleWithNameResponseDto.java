package com.example.schedule.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleWithNameResponseDto {


    private final String  username;

    private final String title;

    private final String detail;


    public ScheduleWithNameResponseDto(String title, String detail, String username) {
        this.title = title;
        this.detail = detail;
        this.username = username;
    }
}
