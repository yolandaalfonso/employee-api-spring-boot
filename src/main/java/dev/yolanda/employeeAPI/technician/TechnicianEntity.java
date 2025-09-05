package dev.yolanda.employeeAPI.technician;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TechnicianEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String technicianName;

    public TechnicianEntity() {
    }

    public TechnicianEntity(String technicianName) {
        this.technicianName = technicianName;
    }

    public TechnicianEntity(Long id, String technicianName) {
        this.id = id;
        this.technicianName = technicianName;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    

    
}
