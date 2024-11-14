package com.example.schedule.controller;


import com.example.schedule.common.Const;
import com.example.schedule.dto.user.*;
import com.example.schedule.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginResponseDtoResponseEntity(
            @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request){
        LoginResponseDto loginResponseDto =
                userService.findUserByEmailAndPasswordOrElseThrow(
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );
        Long userId = loginResponseDto.getId();
        UserResponseDto loginUser = userService.findById(userId);
        HttpSession session = request.getSession();

        //session 에 loginUser 저장
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(loginResponseDto , HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
            return "logout";
        }

        return "session not exist";
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
