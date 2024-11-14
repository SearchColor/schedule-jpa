package com.example.schedule.repository;

import com.example.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmailAndPassword(String email , String  password);

    default User findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = "+id));
    }

    default User findUserByUsernameOrElseThrow(String username){
        return findUserByUsername(username)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist username = "+username));
    }

    default User findUserByEmailAndPasswordOrElseThrow(String email , String  password){
        return findUserByEmailAndPassword(email,password)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist user"));
    }
}
