<p align="center">
<img src="https://cdn.imweb.me/thumbnail/20231120/9b1551ea109cf.png" width="40%" height="30%" title="px(픽셀) 크기 설정" alt="Calculator"></img>
</p>

# 📌 Schdule Management Application

>- ## ⚙ 구현 기능
>1. User 생성,조회,삭제
>2. password encode(암호화)
>3. User login (session)
>4. Schedule 생성,조회,수정,삭제
>5. Schedule Paging
>6. Comment 생성,조회,수정,삭제
>7. Validation 예외처리
>8. CustomException 예외처리
---
## 👷‍♂️ API 명세서

User 등록



method : POST  

URI : /users/signup

request : @RequestBody

    {
        "username": "user1",
        "password": "1234",
        "email": "aa@aa.com"
    }

response : 
    
    //정상등록 예시 
    {   
        "id": 1,
        "username": "user1",
        "email": "aa@aa.com",
        "password": "$2a$04$SAdzROzAX./dInQwejqhheaaA9G.uWzj2sv0S20TSWf87/nal/VAS"
    }

    //실패 예시
    {
        "password": "password 은 1~10 글자여야 합니다.",
        "email": "이메일 형식이 올바르지 않습니다.",
        "username": "username 은 1~5 글자여야 합니다."
    }

status code : 

201(Created) : 정상등록 

400(Bad_Request) : 실패

---
유저 조회

method : GET

URI : /users/{id}

request :  -

response :

    //정상조회 예시 
    {   
        "username": "user1",
        "email": "aa@aa.com",
        "password": "$2a$04$CJYd4Qr8n3/ol9KKLPTYyukHvCVEevOKpH.vrYI7R.IuiTAmZcMny"
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T05:57:49.734068",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "USER_NOT_FOUND",
        "message": "해당 id 로 인한 유저정보를 찾을 수 없습니다"
    }

status code :

200(OK) : 정상조회

404(NOT_FOUND) : 실패

---

유저 삭제


method : DELETE

URI : /users/{id}

request :  -

response :

    //정상 삭제 예시
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T06:11:38.465995",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_USER",
        "message": "권한이 없습니다. 해당유저만 가능합니다."
    }

status code :

200(OK) : 정상삭제

401(UNAUTHORIZED) : 실패

---

유저 로그인

method : POST

URI : /users/login

request :  -

response :

    //정상 로그인 예시
    {
        "email" : "aa@aa.com",
        "password" : "1234"
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T11:43:23.341849",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_PASSWORD",
        "message": "password 가 일치하지 않습니다."
    }

status code :

200(OK) : 정상 로그인

401(UNAUTHORIZED) : 실패

---

유저 로그아웃

method : POST

URI : /users/logout

request :  -

response :

    //정상 session삭제 예시
    logout

    //실패 예시
    session not exist
    

status code :

200(OK) : 정상

---

일정 등록

method : POST

URI : /schedules

request :  
    
    {
        "username" : "user1",
        "title" : "user1 제목1",
        "detail" : "user1 할일"
    }

response :

    //정상 등록 예시
    
    {
        "id": 1,
        "title": "user1 제목1",
        "detail": "user1 할일"
    }

    //실패 예시
    {
        "detail": "detail 은 20글자 이내여야 합니다.",
        "title": "title 은 10글자 이내여야 합니다.",
        "username": "username 은 1~5 글자여야 합니다."
    }

status code :

200(OK) : 정상

400(BAD_REQUEST) : 실패

500(Internal Server Error) : 로그인 안돼어있을 시 - > 수정 필요

---
일정 조회

method : GET

URI : /schedules/{id}

request :   -

    

response :

    //정상  예시
    
    [
        {
            "id": 2,
            "title": "user1 제목",
            "detail": "user1 할일"
        }
    ]

    //data 없을시 (빈배열 반환)
    
    []

status code :

200(OK) : 정상

---

일정 조회 (페이징)

method : GET

URI : /schedules/paging

request :   @RequestParams

@RequestParams 없을시 @PageableDefault



response :

    //정상  예시
    
    [
        {
            "createdAt": "2024-11-15T12:59:58.938111",
            "modifiedAt": "2024-11-15T12:59:58.938111",
            "id": 6,
            "user": {
                "createdAt": "2024-11-15T12:50:01.623678",
                "modifiedAt": "2024-11-15T12:50:01.623678",
                "id": 1,
                "username": "user1",
                "password": "$2a$04$MJhgFdFNdQDa0Ex6PpkE0.DYhatAMX0MnQp8WDJnWSrkRXT9ixtoi",
                "email": "aa@aa.com"
            },
            "title": "user1 제목",
            "detail": "user1 할일"
        },

            '''

    ]

    //data 없을시 (빈배열 반환)
    
    []

status code :

200(OK) : 정상




---
일정 수정

method : PUT

URI : /schedules/{id}

request :

    {
        "password" : "1234",
        "title" : "수정된 제목1",
        "detail" : "수정된 할일1"
    }

response :

    //정상 등록 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T12:50:30.135648",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_PASSWORD",
        "message": "password 가 일치하지 않습니다."
    }

status code :

200(OK) : 정상

400(Bad_Request) : validation 예외
401(UNAUTHORIZED) : password 실패

----

일정 삭제

method : DELETE

URI : /schedules/{id}

request : -

(로그인된 유저에 관한 data 만 삭제가 가능)

ex) 다른유저의 data 삭제 불가



response :

    //정상 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T12:52:52.475839",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "SCHEDULE_NOT_FOUND",
        "message": "해당 id 로 인한 일정정보를 찾을 수 없습니다"
    }

---


댓글 등록

method : POST

URI : /comments

request :

    {
        "scheduleid" : 1,
        "detail" : "user1댓글"
    }

response :

    //정상 등록 예시
    
    {
        "id": 1,
        "scheduleid": 1,
        "username": "user1",
        "detail": "user1댓글"
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T13:06:29.784908",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "SCHEDULE_NOT_FOUND",
        "message": "해당 id 로 인한 일정정보를 찾을 수 없습니다"
    }


---

댓글 조회

method : GET

URI : /comments/{id}

request : - 

    

response :

    //정상 예시
    
    {
        "id": 1,
        "scheduleid": 7,
        "username": "user1",
        "detail": "user1댓글"
    }

    //실패 예시
    {
        "timestamp": "2024-11-15T13:07:39.71697",
        "status": 404,
        "error": "NOT_FOUND",
        "code": "COMMENT_NOT_FOUND",
        "message": "해당 id 로 인한 댓글정보를 찾을 수 없습니다"
    }

---

댓글 수정

method : PUT

URI : /comments/{id}

request :

    {
        "detail" : "dfdfdfdf"
    }

response :

    //성공 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T13:11:36.239848",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_USER",
        "message": "권한이 없습니다. 해당유저만 가능합니다."
    }

status code :

200(OK) : 정상

400(Bad_Request) : validation 예외
401(UNAUTHORIZED) : 권한 예외
404(NOT_FOUND) : id 조회 실패

---

댓글 삭제

method : DELETE

URI : /comments/{id}

request :  -



response :

    //성공 예시
    
    1

    //실패 예시
    {
        "timestamp": "2024-11-15T13:11:36.239848",
        "status": 401,
        "error": "UNAUTHORIZED",
        "code": "UNAUTHORIZED_USER",
        "message": "권한이 없습니다. 해당유저만 가능합니다."
    }

status code :

200(OK) : 정상


401(UNAUTHORIZED) : 권한 예외
404(NOT_FOUND) : id 조회 실패

---

### password 암호화


- passwordEncoder 를 사용하여 패스워드를 encode 
    ```java
        String encodePassword = passwordEncoder.encode(password);
        User user = new User(username , encodePassword , email);
        User saveUser = userRepository.save(user);
    ```

- matches() method 를 사용하여 판별
    ```java
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        if (!passwordEncoder.matches(password , writer.getPassword())){
              throw new CustomException(UNAUTHORIZED_PASSWORD);
        }
    ```
---

### Paging

@PageableDefault 사용하여 기본값을 설정 

기준 : 수정일 , 내림차순
```java
    @GetMapping("/paging")
    public List<Schedule> findScheduleByPageRequest(
        @PageableDefault(size = 10 , sort = "modifiedAt", 
                direction = Sort.Direction.DESC) Pageable pageable
        ){
        log.info("sorted = {}" ,pageable.getSort());
        return scheduleService.findScheduleByPageRequest(pageable);
    }
```

```java  
    //paging 처리
    public List<Schedule>findScheduleByPageRequest(Pageable pageable){
        return scheduleRepository.findAll(pageable).getContent();
    }
```
---

### Validation 예외처리


- 제약 조건 설정
```java
    @NotBlank(message = "username 은 필수값 입니다.")
    @Size(min = 1, max = 5, message = "username 은 1~5 글자여야 합니다.")
    private final String username;

    @NotBlank(message = "email 은 필수값 입니다.")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message = "password 는 필수값 입니다.")
    @Size(min = 1,max = 10, message = "password 은 1~10 글자여야 합니다.")
    private final String password;
```

검증할 객체에 @Validated 적용
```java
    @PostMapping("/signup")
    public ResponseEntity<?> signUP(
                @Validated @RequestBody SignUpRequestDto requestDto,
                BindingResult bindingResult
        ){
            //Validation 예외 처리
            ResponseEntity<?> errorMap = getResponseEntity(bindingResult);
            if (errorMap != null) return errorMap;
    
            SignUpResponseDto signUpResponseDto =
                    userService.signUp(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            requestDto.getEmail()
                    );
            return new ResponseEntity<>(signUpResponseDto , HttpStatus.CREATED);
        }
```



---
ERD
==

![스크린샷 2024-11-15 오전 4 55 45](https://github.com/user-attachments/assets/6ad11043-76a1-4402-89c6-704b028c30e8)