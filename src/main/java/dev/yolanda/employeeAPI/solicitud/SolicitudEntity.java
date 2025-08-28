package dev.yolanda.employeeAPI.solicitud;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    
    public SolicitudEntity() {
    }


    public SolicitudEntity(String applicantName, LocalDate applicationDate, String subject, String description) {
        this.applicantName = applicantName;
        this.applicationDate = applicationDate;
        this.subject = subject;
        this.description = description;
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

    

}
