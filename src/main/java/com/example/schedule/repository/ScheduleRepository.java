package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import com.example.schedule.errors.errorcode.ErrorCode;
import com.example.schedule.errors.exception.CustomException;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule , Long> {


    default Schedule findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(()->
                        new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
    }





}
