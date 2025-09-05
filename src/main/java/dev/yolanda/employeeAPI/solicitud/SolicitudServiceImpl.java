package dev.yolanda.employeeAPI.solicitud;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.yolanda.employeeAPI.exceptions.SolicitudNotFoundExceptions;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;
import dev.yolanda.employeeAPI.solicitud.mappers.SolicitudMapper;
import dev.yolanda.employeeAPI.technician.TechnicianEntity;
import dev.yolanda.employeeAPI.technician.TechnicianRepository;

@Service
public class SolicitudServiceImpl implements InterfaceSolicitudService{

    private final SolicitudRepository repository;
    private final TechnicianRepository technicianRepository;

    public SolicitudServiceImpl(SolicitudRepository repository, TechnicianRepository technicianRepository) {
        this.repository = repository;
        this.technicianRepository = technicianRepository;
    }

    @Override
    public List<SolicitudDTOResponse> getEntities() {
         List<SolicitudDTOResponse> solicitudes = new ArrayList<>();

        repository.findAll().forEach(c -> {
            SolicitudDTOResponse solicitud = SolicitudMapper.toDTO(c);
            solicitudes.add(solicitud);
        });

        return solicitudes;
    }

    @Override
    public SolicitudDTOResponse storeEntity(SolicitudDTORequest solicitudDTORequest) {
        SolicitudEntity solicitud = SolicitudMapper.toEntity(solicitudDTORequest);
        SolicitudEntity solicitudStored = repository.save(solicitud);
        return SolicitudMapper.toDTO(solicitudStored) ;
    }

    @Override
    public List<SolicitudDTOResponse> getEntitiesOrdered() {
        return repository.findAllByOrderByApplicationDateAsc()
                         .stream()
                         .map(SolicitudMapper::toDTO)
                         .toList();
    }

    public List<SolicitudDTOResponse> getPendientes() {
        return repository.findByAtendidaFalseOrderByApplicationDateAsc()
                         .stream()
                         .map(SolicitudMapper::toDTO)
                         .toList();
    }

    /* @Override
    public SolicitudDTOResponse marcarComoAtendida(Long id, String tecnico) {
        SolicitudEntity solicitud = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id " + id));

        solicitud.setAtendida(true);
        solicitud.setAtendidaPor(tecnico);

        SolicitudEntity updated = repository.save(solicitud);
        return SolicitudMapper.toDTO(updated);
    } */

    @Override
    public SolicitudDTOResponse marcarComoAtendida(Long id, Long technicianId) { // Cambié el parámetro a Long
        SolicitudEntity solicitud = repository.findById(id)
            .orElseThrow(() -> new SolicitudNotFoundExceptions("Solicitud no encontrada"));
        
        // BUSCAR EL TÉCNICO POR ID
        TechnicianEntity tecnico = technicianRepository.findById(technicianId)
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));
        
        // AHORA SÍ PUEDES ASIGNAR EL TÉCNICO
        solicitud.setAtendida(true);
        solicitud.setAtendidaPor(tecnico);  // TechnicianEntity, no String
        solicitud.setFechaAtencion(LocalDateTime.now());
        
        SolicitudEntity updated = repository.save(solicitud);
        return SolicitudMapper.toDTO(updated);
    }


    @Override
    public SolicitudDTOResponse showById(Long id) {
        SolicitudEntity solicitud = repository.findById(id).orElseThrow(() -> new SolicitudNotFoundExceptions("Solicitud no encontrada. Id " + id + " no existe."));
        return SolicitudMapper.toDTO(solicitud);
    }

    /* @Override
    public SolicitudDTOResponse updateSolicitud(Long id, SolicitudDTORequest dtoRequest) {
    SolicitudEntity solicitud = repository.findById(id)
        .orElseThrow(() -> new SolicitudNotFoundExceptions("Solicitud no encontrada con id " + id));

    solicitud.setApplicantName(dtoRequest.applicantName());
    solicitud.setApplicationDate(dtoRequest.applicationDate());
    solicitud.setSubject(dtoRequest.subject());
    solicitud.setDescription(dtoRequest.description());
    solicitud.setFechaEdicion(LocalDateTime.now());

    // Si viene un técnico nuevo en el request, lo buscamos
    if (dtoRequest.technicianId() != null) {
        TechnicianEntity tecnico = technicianRepository.findById(dtoRequest.technicianId())
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado con id " + dtoRequest.technicianId()));
        solicitud.setAtendidaPor(tecnico);
    }

    SolicitudEntity updated = repository.save(solicitud);
    return SolicitudMapper.toDTO(updated);
    } */

    @Override
    public SolicitudDTOResponse updateSolicitud(Long id, SolicitudDTORequest dtoRequest) {
    SolicitudEntity solicitud = repository.findById(id)
            .orElseThrow(() -> new SolicitudNotFoundExceptions("Solicitud no encontrada"));

    TechnicianEntity tecnico = null;
    if (dtoRequest.technicianId() != null) {
        tecnico = technicianRepository.findById(dtoRequest.technicianId())
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));
    }

    SolicitudMapper.updateEntity(solicitud, dtoRequest, tecnico);

    SolicitudEntity updated = repository.save(solicitud);
    return SolicitudMapper.toDTO(updated);
}


    @Override
    public void deleteIfAtendida(Long id) {
    SolicitudEntity solicitud = repository.findById(id)
            .orElseThrow(() -> new SolicitudNotFoundExceptions("Solicitud no encontrada"));

    if (!solicitud.isAtendida()) {
        throw new RuntimeException("No se puede eliminar una solicitud que aún está pendiente");
    }

    repository.delete(solicitud);
}


    




}
