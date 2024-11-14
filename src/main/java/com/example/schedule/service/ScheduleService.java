package com.example.schedule.service;


import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.dto.schedule.ScheduleWithNameResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.errors.exception.CustomException;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.schedule.errors.errorcode.ErrorCode.UNAUTHORIZED_PASSWORD;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;



    public ScheduleResponseDto save(String username, String title, String detail){

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title , detail);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle() , schedule.getDetail());
    }

    public List<ScheduleResponseDto> findAll(){
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleWithNameResponseDto findById(Long id){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User writer = findSchedule.getUser();

        return new ScheduleWithNameResponseDto(findSchedule.getTitle(), findSchedule.getDetail() , writer.getUsername());
    }

    @Transactional
    public void updateSchedule(Long id, String password, String title, String detail){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        User writer = findSchedule.getUser();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        if (!passwordEncoder.matches(password , writer.getPassword())){
            throw new CustomException(UNAUTHORIZED_PASSWORD);
        }

        findSchedule.updateTitleAndDetail(title, detail);

    }

    public void delete(Long id){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findSchedule);
    }
}
