package com.challenge.clinicAPI.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ValidationErrorData::new).toList();

        return ResponseEntity.badRequest().body(errors);
    }

    //DTO used only here
    private record ValidationErrorData(String field, String error){
        public  ValidationErrorData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
