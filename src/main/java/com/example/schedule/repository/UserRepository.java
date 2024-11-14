package com.example.schedule.repository;

import com.example.schedule.entity.User;
import com.example.schedule.errors.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

import static com.example.schedule.errors.errorcode.ErrorCode.USERNAME_NOT_FOUND;
import static com.example.schedule.errors.errorcode.ErrorCode.USER_NOT_FOUND;

public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);

    default User findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(()->
                        new CustomException(USER_NOT_FOUND));
    }

    default User findUserByUsernameOrElseThrow(String username){
        return findUserByUsername(username)
                .orElseThrow(()->
                        new CustomException(USERNAME_NOT_FOUND));
    }


}
