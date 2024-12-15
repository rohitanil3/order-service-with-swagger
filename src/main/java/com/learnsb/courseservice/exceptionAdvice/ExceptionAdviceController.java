package com.learnsb.courseservice.exceptionAdvice;


import com.learnsb.courseservice.dto.ServiceResponse;
import com.learnsb.courseservice.exception.CustomDAOException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdviceController {

    //bean validator exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ServiceResponse<Map<String,String>> globalExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error-> errors.put(error.getField(),error.getDefaultMessage()));
        return new ServiceResponse<>(HttpStatus.BAD_REQUEST,errors);
    }
    // Handle RuntimeExceptions (e.g., NullPointerException, IllegalArgumentException)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ServiceResponse<Map<String,String>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("message", ex.getMessage());
        response.put("details", request.getDescription(false));
        System.out.println("runtime ex handle");

        return new ServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,response);
    }

    // Handle DAO Exceptions (e.g., issues with database)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomDAOException.class)
    public ServiceResponse<Map<String,String>> handleDataAccessException(CustomDAOException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("message", "Database error occurred");
        response.put("details", ex.getMessage()); // Avoid exposing sensitive details in production
        System.out.println("db ex handle");
        return new ServiceResponse<>(HttpStatus.BAD_REQUEST,response);
    }

    // Handle Other Exceptions (Generic handler)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ServiceResponse<Map<String,String>> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("message", "An unexpected error occurred");
        response.put("details", ex.getMessage());
        System.out.println("generic all ex handle");

        return new ServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,response);
    }
}
