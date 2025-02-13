package com.organization.employeeManagement.exception;

public class IncompleteInformation extends RuntimeException {
    public IncompleteInformation() {
    }

    public IncompleteInformation(String message) {
        super(message);
    }
}
