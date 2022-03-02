package com.nigelxiaido.nigeliaidoperson.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<Object> handleExpenseNotFoundException(PersonNotFoundException ex , WebRequest req) {
        return new ResponseEntity<>(new CustomErrorResponse(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND.toString()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex , WebRequest req) {
        return new ResponseEntity<>(new CustomErrorResponse(new Date(), ex.getMessage(), "Bad Request Exception"),HttpStatus.BAD_REQUEST);
    }

}