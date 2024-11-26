package com.oocl.springbootemployee.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private static final String EMPLOYEE_NOT_FOUND = "Employee not found";

    public EmployeeNotFoundException() {
        super(EMPLOYEE_NOT_FOUND);
    }
}
