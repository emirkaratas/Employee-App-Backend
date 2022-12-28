package com.example.employee.extensions;

import com.example.employee.entities.errorModel.ErrorDetails;
import com.example.employee.entities.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorDetails.builder()
                        .statusCode(404)
                        .message(ex.getMessage())
                        .path(request.getDescription(false))
                        .build()
                );
    }
}