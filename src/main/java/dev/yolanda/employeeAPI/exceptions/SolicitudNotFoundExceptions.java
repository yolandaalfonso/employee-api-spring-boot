package dev.yolanda.employeeAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Solicitud no encontrada")
public class SolicitudNotFoundExceptions extends SolicitudExceptions {
    
    public SolicitudNotFoundExceptions(String message) {
        super(message);
    }

    public SolicitudNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
