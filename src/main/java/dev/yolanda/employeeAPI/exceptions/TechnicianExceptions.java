package dev.yolanda.employeeAPI.exceptions;

public class TechnicianExceptions extends RuntimeException{
    public TechnicianExceptions(String message) {
        super(message);
    }

    public TechnicianExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
