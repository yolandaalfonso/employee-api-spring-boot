package dev.yolanda.employeeAPI.solicitud;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitudController {
    
    @GetMapping("solicitudes")
    public SolicitudEntity index() {

        SolicitudEntity solicitud1 = new SolicitudEntity("Maria", LocalDate.of(2025, 8, 27), "vacaciones", "petición de vacaciones");

        // class -> json = serializar . json -> class = deserializar
        return solicitud1;
    }
}
