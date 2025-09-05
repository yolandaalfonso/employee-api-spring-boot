package dev.yolanda.employeeAPI.solicitud;

import java.util.List;

import dev.yolanda.employeeAPI.implementations.IGenericService;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;

public interface InterfaceSolicitudService extends IGenericService<SolicitudDTOResponse, SolicitudDTORequest> {

    //public List<SolicitudEntity> getSolicitudEntities();
    List<SolicitudDTOResponse> getEntitiesOrdered();
    List<SolicitudDTOResponse> getPendientes();
    SolicitudDTOResponse marcarComoAtendida(Long id, Long technicianId);
}
