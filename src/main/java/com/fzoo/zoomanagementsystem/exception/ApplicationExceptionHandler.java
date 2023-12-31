package com.fzoo.zoomanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public Map<String, String> handleInvalidDateException(DateTimeParseException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "The format should be yyyy-MM-dd and the day should be valid");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyStringException.class)
    public Map<String, String> handleEmptyStringException(EmptyStringException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NegativeValueException.class)
    public Map<String, String> handleNegativeException(NegativeValueException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "Can not input negative value");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MealCreatedException.class)
    public Map<String, String> handleMealCreatedException(MealCreatedException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "Meal was created");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongMeasureException.class)
    public Map<String, String> handleWrongMeasureException(WrongMeasureException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "This measure does not support this type");
        return errorMap;
    }

}
