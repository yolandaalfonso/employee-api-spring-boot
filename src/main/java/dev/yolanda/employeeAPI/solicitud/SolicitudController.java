package dev.yolanda.employeeAPI.solicitud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitudController {
    
    @GetMapping("solicitudes")
    public SolicitudEntity index() {

        SolicitudEntity solicitud1 = new SolicitudEntity("Maria", 2025-08-27, "vacaciones", "petición de vacaciones");

        // class -> json = serializar . json -> class = deserializar
        return solicitud1;
    }
}
