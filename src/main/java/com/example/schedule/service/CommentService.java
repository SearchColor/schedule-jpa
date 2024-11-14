package com.example.schedule.service;

import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.errors.exception.CustomException;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.schedule.errors.errorcode.ErrorCode.UNAUTHORIZED_USER;

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

    public CommentResponseDto findById(Long id){
        Comment comment = commentRepository.findByIdOrElseThrow(id);
        return new CommentResponseDto(comment.getId(),comment.getSchedule().getId(),comment.getUser().getUsername(),comment.getDetail());
    }

    @Transactional
    public void updateComment (Long id , String username , String detail){
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        Long commentUserid=findComment.getUser().getId();
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);
        //수정하려는 유저가 작성자인지 판별
        if (commentUserid != findUser.getId()){
            throw new CustomException(UNAUTHORIZED_USER);
        }
        findComment.updateDetail(detail);
    }

    public void delete(Long id, String username){
        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        Long commentUserid=findComment.getUser().getId();
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);
        //수정하려는 유저가 작성자인지 판별
        if (commentUserid != findUser.getId()){
            throw new CustomException(UNAUTHORIZED_USER);
        }
        commentRepository.delete(findComment);
    }

}
