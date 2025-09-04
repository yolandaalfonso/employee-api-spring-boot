package dev.yolanda.employeeAPI.solicitud.dtos;

import java.time.LocalDate;

public record SolicitudDTOResponse(Long id, String applicantName, LocalDate applicationDate, String subject, String description) {

}
