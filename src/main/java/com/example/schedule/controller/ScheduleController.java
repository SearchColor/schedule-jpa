package com.example.schedule.controller;


import com.example.schedule.dto.schedule.CreateScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.dto.schedule.ScheduleWithNameResponseDto;
import com.example.schedule.dto.schedule.UpdateScheduleRequestDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> save(
            @Validated @RequestBody CreateScheduleRequestDto requestDto,
            BindingResult bindingResult){

        ResponseEntity<?> errorMap = getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(
                        requestDto.getUsername(),
                        requestDto.getTitle(),
                        requestDto.getDetail()
                );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithNameResponseDto> findById(@PathVariable Long id){

        ScheduleWithNameResponseDto scheduleWithNameResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleWithNameResponseDto,HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTitleAndDetail(
            @PathVariable Long id,
            @Validated @RequestBody UpdateScheduleRequestDto requestDto,
            BindingResult bindingResult
            ){

        ResponseEntity<?> errorMap = getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        scheduleService.updateSchedule(id, requestDto.getPassword(),requestDto.getTitle(),requestDto.getDetail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Validation 예외 error 생성
    private ResponseEntity<?> getResponseEntity(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
