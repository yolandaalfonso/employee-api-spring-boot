package dev.yolanda.employeeAPI.solicitud.dtos;

import java.time.LocalDate;

public record SolicitudDTORequest(String applicantName, LocalDate applicationDate, String subject, String description) {

}
