package dev.yolanda.employeeAPI.exceptions;

public class SolicitudExceptions extends RuntimeException{
    public SolicitudExceptions(String message) {
        super(message);
    }

    public SolicitudExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
