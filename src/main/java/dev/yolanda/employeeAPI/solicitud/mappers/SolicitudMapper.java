package dev.yolanda.employeeAPI.solicitud.mappers;

import org.springframework.stereotype.Component;

import dev.yolanda.employeeAPI.solicitud.SolicitudEntity;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;

@Component
public class SolicitudMapper {
    
    public static SolicitudEntity toEntity(SolicitudDTORequest dtoRequest) {
        SolicitudEntity solicitud = new SolicitudEntity();
        solicitud.setApplicantName(dtoRequest.applicantName());

        return solicitud;
    }

    public static SolicitudDTOResponse toDTO(SolicitudEntity entity) {
        SolicitudDTOResponse dtoResponse = new SolicitudDTOResponse(entity.getId(), entity.getApplicantName(), entity.getApplicationDate(), entity.getSubject(), entity.getDescription());

        return dtoResponse;
    }
}
