package com.github.syndexmx.demoairfleet.controller.advices;

import com.github.syndexmx.demoairfleet.exceptions.AirfleetIncorrectIdApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {

    @ExceptionHandler(AirfleetIncorrectIdApiRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handlerRequestException(AirfleetIncorrectIdApiRequestException ex) {
        log.error("API EXCEPTION: \n" + ex.toString());
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}
