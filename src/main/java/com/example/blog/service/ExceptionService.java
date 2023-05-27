package com.example.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionService {

    public int exceptionTest(int a) {
        int b = 10;
        int c = 0;
        try {
            c = (a+b)/ 0;
        }
        catch(Exception e) {
            //로그 끝에 예외 객체인 e를 추가하면, 스택 스테이스 출력해줌
            log.info("error message :{} ",e.getMessage(),e);
        }
        finally {
            log.info("finally문 수행");
        }
        return c;
    }

    public int exceptionThrowTest(int a) throws Exception{
        int b = 10;
        int c = 0;
        c = (a+b)/ 0;

        return c;
    }

}

