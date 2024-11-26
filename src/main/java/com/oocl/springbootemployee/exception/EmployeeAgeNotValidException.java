package com.oocl.springbootemployee.exception;

public class EmployeeAgeNotValidException extends RuntimeException{

    private static final String AGE_NOT_VALID = "Employee age is not valid";
    public EmployeeAgeNotValidException() {
        super(AGE_NOT_VALID);

    }
}
