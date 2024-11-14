package com.example.schedule.controller;


import com.example.schedule.dto.user.SignUpRequestDto;
import com.example.schedule.dto.user.SignUpResponseDto;
import com.example.schedule.dto.user.UserResponseDto;
import com.example.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUP(@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getEmail()
                );
        return new ResponseEntity<>(signUpResponseDto , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
