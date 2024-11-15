package com.example.schedule.filter;

import com.example.schedule.errors.errorcode.ErrorCode;
import com.example.schedule.errors.exception.CustomException;
import com.example.schedule.errors.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PatternMatchUtils;

import javax.lang.model.type.ErrorType;
import java.io.IOException;

import static com.example.schedule.errors.errorcode.ErrorCode.USER_NOT_FOUND;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/users/signup","/logout","/users/login","/users/*","/session"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse =(HttpServletResponse) servletResponse;

        log.info("로그인 필터 실행");

        //white list 에 포함된 경우 true 가 반환되면서 if 내부로직 진행
        if (!isWhiteList(requestURI)){
            HttpSession session = httpRequest.getSession(false);

            if (session == null ){
//               throw new CustomException(USER_NOT_FOUND);
               throw new RuntimeException("로그인해주세요");
            }
            //로그인 성공로직
            log.info("로그인에 성공했습니다");

        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }


}
