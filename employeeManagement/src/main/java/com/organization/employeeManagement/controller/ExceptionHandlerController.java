package com.organization.employeeManagement.controller;

import com.organization.employeeManagement.exception.ErrorResponse;
import com.organization.employeeManagement.exception.IncompleteInformation;
import com.organization.employeeManagement.exception.RecordAlreadyExist;
import com.organization.employeeManagement.exception.RecordDoesNotExist;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.access.AccessDeniedException;

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

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> expiredJwtToken(ExpiredJwtException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> signatureMismatch(SignatureException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
