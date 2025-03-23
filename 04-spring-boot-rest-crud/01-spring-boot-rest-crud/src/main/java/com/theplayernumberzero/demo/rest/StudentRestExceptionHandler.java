package com.theplayernumberzero.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    //add all exceptions here
    //exception handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        //return responseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //for catch all exceptions
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleAllExceptions(Exception exc){
        //create StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        //return responseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
