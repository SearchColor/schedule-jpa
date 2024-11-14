package com.example.schedule.dto.comment;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    private final Long scheduleid;

    private final String detail;

    public CreateCommentRequestDto(Long scheduleid, String detail) {
        this.scheduleid = scheduleid;
        this.detail = detail;
    }
}
