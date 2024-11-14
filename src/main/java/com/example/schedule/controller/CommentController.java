package com.example.schedule.controller;


import com.example.schedule.common.Const;
import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.dto.comment.CreateCommentRequestDto;
import com.example.schedule.dto.comment.UpdateCommentRequestDto;
import com.example.schedule.dto.user.UserResponseDto;
import com.example.schedule.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

        //validation 예외 처리
        ResponseEntity<?> errorMap = getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        HttpSession session = request.getSession(false);
        //login 되어있는 user data
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        CommentResponseDto commentResponseDto = commentService.save(requestDto.getScheduleid() , loginUser.getUsername() , requestDto.getDetail());

        return new ResponseEntity<>(commentResponseDto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id){
        CommentResponseDto commentResponseDto = commentService.findById(id);
        return new ResponseEntity<>(commentResponseDto , HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetail(
            @PathVariable Long id,
            @RequestBody Map<String, String> detailMap,
            HttpServletRequest request){

        HttpSession session = request.getSession(false);
        //login 되어있는 user data
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        commentService.updateComment(id,loginUser.getUsername() , detailMap.get("detail"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        //login 되어있는 user data
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        commentService.delete(id,loginUser.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    //중복되는 예외처리
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
