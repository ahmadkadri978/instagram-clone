package com.instagram.clone.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex , WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage() , HttpStatus.NOT_FOUND.value() , request.getDescription(false));
        return  new ResponseEntity<>(errorResponse , HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex , WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred" , HttpStatus.INTERNAL_SERVER_ERROR.value() , request.getDescription(false));
        return new ResponseEntity<>(errorResponse , HttpStatus.INTERNAL_SERVER_ERROR );

    }
}
