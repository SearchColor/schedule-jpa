package com.example.schedule.service;

import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto save(Long scheduleid, String username , String detail){
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleid);
        Comment comment = new Comment(detail);
        comment.setUser(findUser);
        comment.setSchedule(findSchedule);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(),comment.getSchedule().getId(), comment.getUser().getUsername() , comment.getDetail());
    }
}
