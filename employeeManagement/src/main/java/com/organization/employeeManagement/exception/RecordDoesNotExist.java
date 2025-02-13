package com.organization.employeeManagement.exception;

public class RecordDoesNotExist extends RuntimeException{
    public RecordDoesNotExist() {
    }

    public RecordDoesNotExist(String message) {
        super(message);
    }
}
