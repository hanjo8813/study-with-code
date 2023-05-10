package com.example.practicelogback.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public RedirectView catchError(){
        log.error("에러터짐");
        return new RedirectView("/");
    }

}
