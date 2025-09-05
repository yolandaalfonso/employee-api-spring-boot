package dev.yolanda.employeeAPI.solicitud;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.yolanda.employeeAPI.technician.TechnicianEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="solicitudes")
public class SolicitudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String applicantName;
    private LocalDate applicationDate;
    private String subject;
    private String description;


    private boolean atendida = false;


    @ManyToOne
    private TechnicianEntity atendidaPor;
    

    private LocalDateTime fechaAtencion;
    private LocalDateTime fechaEdicion;



    public SolicitudEntity() {
    }


    public SolicitudEntity(Long id, String applicantName, LocalDate applicationDate, String subject, String description) {
        this.id = id;
        this.applicantName = applicantName;
        this.applicationDate = applicationDate;
        this.subject = subject;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getApplicantName() {
        return applicantName;
    }


    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }


    public LocalDate getApplicationDate() {
        return applicationDate;
    }


    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAtendida() {
        return atendida;
    }


    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }


    public TechnicianEntity getAtendidaPor() {
        return atendidaPor;
    }


    public void setAtendidaPor(TechnicianEntity atendidaPor) {
        this.atendidaPor = atendidaPor;
    }

    public LocalDateTime getFechaAtencion() {
        return fechaAtencion;
    }


    public void setFechaAtencion(LocalDateTime fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }


    public LocalDateTime getFechaEdicion() {
        return fechaEdicion;
    }


    public void setFechaEdicion(LocalDateTime fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }    

    public boolean isPendiente() {
        return !this.atendida;
    }

}
