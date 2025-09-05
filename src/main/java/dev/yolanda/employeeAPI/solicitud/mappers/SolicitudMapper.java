package dev.yolanda.employeeAPI.solicitud.mappers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.yolanda.employeeAPI.solicitud.SolicitudEntity;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;
import dev.yolanda.employeeAPI.technician.TechnicianEntity;

@Component
public class SolicitudMapper {
    
    public static SolicitudEntity toEntity(SolicitudDTORequest dtoRequest) {
        SolicitudEntity solicitud = new SolicitudEntity();
        solicitud.setApplicantName(dtoRequest.applicantName());

        return solicitud;
    }

    public static SolicitudDTOResponse toDTO(SolicitudEntity entity) {
        SolicitudDTOResponse dtoResponse = new SolicitudDTOResponse(
            entity.getId(),
            entity.getApplicantName(),
            entity.getApplicationDate(),
            entity.getSubject(),
            entity.getDescription(),
            entity.isAtendida(),
            entity.getAtendidaPor() != null ? entity.getAtendidaPor().getTechnicianName() : null,
            entity.getFechaAtencion(),
            entity.getFechaEdicion()
        );

        return dtoResponse;
    }

    public static void updateEntity(SolicitudEntity entity, SolicitudDTORequest dtoRequest, TechnicianEntity tecnico) {
        if (dtoRequest.applicantName() != null) entity.setApplicantName(dtoRequest.applicantName());
        if (dtoRequest.applicationDate() != null) entity.setApplicationDate(dtoRequest.applicationDate());
        if (dtoRequest.subject() != null) entity.setSubject(dtoRequest.subject());
        if (dtoRequest.description() != null) entity.setDescription(dtoRequest.description());

        // Si se pasa un técnico, marcamos como atendida
        if (tecnico != null) {
            entity.setAtendida(true);
            entity.setAtendidaPor(tecnico);
            entity.setFechaAtencion(LocalDateTime.now());
        }

        // Registrar siempre fecha de edición
        entity.setFechaEdicion(LocalDateTime.now());
    }

}
