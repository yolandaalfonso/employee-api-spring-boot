package dev.yolanda.employeeAPI.solicitud;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.yolanda.employeeAPI.implementations.IGenericService;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;

@RestController
@RequestMapping(path = "${api-endpoint}/solicitudes")
public class SolicitudController {

    private final IGenericService<SolicitudDTOResponse, SolicitudDTORequest> service;

    public SolicitudController(IGenericService<SolicitudDTOResponse, SolicitudDTORequest> service) {
        this.service = service; //Comparar con código original
    }

    @GetMapping("")
    public List<SolicitudDTOResponse> index() {

        // class -> json = serializar . json -> class = deserializar
        return service.getEntities();
    }

    @GetMapping("")
    public List<SolicitudDTOResponse> indexOrdered() {

        // class -> json = serializar . json -> class = deserializar
        return service.getEntitiesOrdered();
    }

    @PostMapping("")
    public ResponseEntity<SolicitudDTOResponse> storeEntity(@RequestBody SolicitudDTORequest dtoRequest) {
        if (dtoRequest.applicantName().isBlank()) return ResponseEntity.badRequest().build();

        SolicitudDTOResponse entityStored = service.storeEntity(dtoRequest);

        if (entityStored == null) return ResponseEntity.noContent().build();

        return ResponseEntity.status(201).body(entityStored);
    }

    // Obtener todas pendientes
    @GetMapping("/pendientes")
    public List<SolicitudDTOResponse> getPendientes() {
        return service.getPendientes();
    }

    // Marcar como atendida
    @PutMapping("/{id}/atender")
    public SolicitudDTOResponse marcarComoAtendida(
            @PathVariable Long id,
            @RequestParam String tecnico
    ) {
        return service.marcarComoAtendida(id, tecnico);
    }

    //@GetMapping("/{id}")
    //public ResponseEntity<SolicitudDTOResponse> show(@PathVariable("id") Long id) {
        //SolicitudDTOResponse solicitud = service.showById(id);
        //return ResponseEntity.ok().body(solicitud);
    //}

}
