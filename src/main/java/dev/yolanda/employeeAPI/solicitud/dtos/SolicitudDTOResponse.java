package dev.yolanda.employeeAPI.solicitud.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.yolanda.employeeAPI.technician.TechnicianEntity;

public record SolicitudDTOResponse(Long id, String applicantName, LocalDate applicationDate, String subject, String description, boolean atendida, TechnicianEntity atendidaPor, LocalDateTime fechaAtencion, LocalDateTime fechaEdicion) {

}
