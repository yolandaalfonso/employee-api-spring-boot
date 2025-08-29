package dev.yolanda.employeeAPI.solicitud;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.yolanda.employeeAPI.implementations.IGenericService;

@RestController
@RequestMapping(path = "${api-endpoint}/solicitudes")
public class SolicitudController {

    private final IGenericService<SolicitudEntity> service;

    public SolicitudController(IGenericService<SolicitudEntity> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<SolicitudEntity> index() {

        // class -> json = serializar . json -> class = deserializar
        return service.getEntities();
    }

    @PostMapping("")
    public SolicitudEntity create(@RequestBody SolicitudEntity solicitud) {
        return service.saveEntity(solicitud);
    }

}
