package com.example.spring_crud.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<Object> handleMethodArugumentNotValid(MethodArgumentNotValidException ex) {

        HashMap<String,String> response=new HashMap<>();
        if (ex.hasErrors()) {
            ex.getBindingResult().getAllErrors().forEach(error->
                    {
                        String fieldError=((FieldError) error).getField();
                        String message=error.getDefaultMessage();
                        response.put(fieldError, message);

                    });
        }
        return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity defaultException(Exception ex)
    {
        return new ResponseEntity(ex,HttpStatus.MULTI_STATUS);
    }
}
