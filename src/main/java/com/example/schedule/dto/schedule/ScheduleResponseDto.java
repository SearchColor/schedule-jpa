package com.example.schedule.dto.schedule;


import com.example.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;

    private final String title;

    private final String detail;


    public ScheduleResponseDto(Long id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public static ScheduleResponseDto toDto (Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(),schedule.getTitle(),schedule.getDetail());
    }


}
