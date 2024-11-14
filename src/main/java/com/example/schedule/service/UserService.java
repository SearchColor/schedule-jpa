package com.example.schedule.service;


import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.dto.user.LoginResponseDto;
import com.example.schedule.dto.user.SignUpResponseDto;
import com.example.schedule.dto.user.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.errors.exception.CustomException;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.example.schedule.errors.errorcode.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder = new PasswordEncoder();

    public SignUpResponseDto signUp(String username , String password , String email){

        //password 암호화
        String encodePassword = passwordEncoder.encode(password);
        User user = new User(username , encodePassword , email);

        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId() , saveUser.getUsername() , saveUser.getEmail() , saveUser.getPassword());
    }

    public UserResponseDto findById(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new CustomException(USER_NOT_FOUND);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(),findUser.getEmail() , findUser.getPassword());
    }

    public void delete(Long id){

        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }

    public LoginResponseDto findUserByEmailAndPasswordOrElseThrow(String email , String password){

        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        User findUser = optionalUser.get();

        if (!passwordEncoder.matches(password , findUser.getPassword())){
            throw new CustomException(UNAUTHORIZED_PASSWORD);
        }
        return new LoginResponseDto(findUser.getId() , findUser.getUsername());
    }




}
