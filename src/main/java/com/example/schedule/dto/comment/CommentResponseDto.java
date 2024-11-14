package com.example.schedule.dto.comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final Long scheduleid;

    private final String username;

    private final String detail;

    public CommentResponseDto(Long id, Long scheduleid, String username, String detail) {
        this.id = id;
        this.scheduleid = scheduleid;
        this.username = username;
        this.detail = detail;
    }
}
