package dev.yolanda.employeeAPI.exceptions;

public class SubjectExceptions extends RuntimeException{
    public SubjectExceptions(String message) {
        super(message);
    }

    public SubjectExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
