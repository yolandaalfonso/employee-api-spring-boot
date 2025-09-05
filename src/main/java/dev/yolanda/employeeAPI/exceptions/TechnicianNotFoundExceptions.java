package dev.yolanda.employeeAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason =  "No se ha encontrado el técnico")
public class TechnicianNotFoundExceptions extends TechnicianExceptions{
    public TechnicianNotFoundExceptions(String message) {
        super(message);
    }

    public TechnicianNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
