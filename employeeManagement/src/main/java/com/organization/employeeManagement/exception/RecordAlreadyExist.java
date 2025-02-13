package com.organization.employeeManagement.exception;

public class RecordAlreadyExist extends RuntimeException {
    public RecordAlreadyExist() {
    }

    public RecordAlreadyExist(String message) {
        super(message);
    }
}
