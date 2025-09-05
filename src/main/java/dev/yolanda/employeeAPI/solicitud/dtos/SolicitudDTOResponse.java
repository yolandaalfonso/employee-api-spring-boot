package dev.yolanda.employeeAPI.solicitud.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SolicitudDTOResponse(Long id, String applicantName, LocalDate applicationDate, String subject, String description, boolean atendida, String technicianName, LocalDateTime fechaAtencion, LocalDateTime fechaEdicion) {

}
