package com.example.schedule.controller;


import com.example.schedule.common.Const;
import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.dto.comment.CreateCommentRequestDto;
import com.example.schedule.dto.schedule.CreateScheduleRequestDto;
import com.example.schedule.dto.user.LoginResponseDto;
import com.example.schedule.dto.user.UserResponseDto;
import com.example.schedule.service.CommentService;
import com.example.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService ;

    @PostMapping
    public ResponseEntity<?> save(
            @Validated @RequestBody CreateCommentRequestDto requestDto,
            BindingResult bindingResult,
            HttpServletRequest request){

        ResponseEntity<?> errorMap = getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        HttpSession session = request.getSession(false);
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        CommentResponseDto commentResponseDto = commentService.save(requestDto.getScheduleid() , loginUser.getUsername() , requestDto.getDetail());

        return new ResponseEntity<>(commentResponseDto,HttpStatus.CREATED);
    }

    private ResponseEntity<?> getResponseEntity(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
