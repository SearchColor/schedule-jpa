package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import com.example.schedule.errors.errorcode.ErrorCode;
import com.example.schedule.errors.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule , Long> {


    default Schedule findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(()->
                        new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
