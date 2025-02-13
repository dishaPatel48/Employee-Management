package com.organization.employeeManagement.controller;

import com.organization.employeeManagement.exception.ErrorResponse;
import com.organization.employeeManagement.exception.IncompleteInformation;
import com.organization.employeeManagement.exception.RecordAlreadyExist;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RecordDoesNotExist.class)
    public ResponseEntity<ErrorResponse> recordDoesNotExist(RecordDoesNotExist r) {
        ErrorResponse errorResponse = new ErrorResponse(r.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(RecordAlreadyExist.class)
    public ResponseEntity<ErrorResponse> recordAlreadyExist(RecordAlreadyExist r) {
        ErrorResponse errorResponse = new ErrorResponse(r.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IncompleteInformation.class)
    public ResponseEntity<ErrorResponse> incompleteInformation(IncompleteInformation r) {
        ErrorResponse errorResponse = new ErrorResponse(r.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
