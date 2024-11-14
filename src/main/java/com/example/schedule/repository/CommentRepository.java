package com.example.schedule.repository;

import com.example.schedule.entity.Comment;
import com.example.schedule.errors.errorcode.ErrorCode;
import com.example.schedule.errors.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Long> {


    default Comment findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(()->
                        new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
