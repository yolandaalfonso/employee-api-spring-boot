package dev.yolanda.employeeAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se ha encontrado el tema")
public class SubjectNotFoundExceptions extends SubjectExceptions{

    public SubjectNotFoundExceptions(String message) {
        super(message);
    }

    public SubjectNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    
}
