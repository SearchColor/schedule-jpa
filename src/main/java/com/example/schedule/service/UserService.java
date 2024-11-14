package com.example.schedule.service;


import com.example.schedule.dto.user.SignUpResponseDto;
import com.example.schedule.dto.user.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username , String password , String email){

        User user = new User(username , password , email);

        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId() , saveUser.getUsername() , saveUser.getEmail() , saveUser.getPassword());
    }

    public UserResponseDto findById(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Does not exist id = " + id);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(),findUser.getEmail() , findUser.getPassword());
    }

    public void delete(Long id){

        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }


}
