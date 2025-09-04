package dev.yolanda.employeeAPI.technician;

import dev.yolanda.employeeAPI.technician.dtos.TechnicianDTORequest;
import dev.yolanda.employeeAPI.technician.dtos.TechnicianDTOResponse;

public class TechnicianMapper {
    public static TechnicianDTOResponse toDTO(TechnicianEntity entity) {

        return new TechnicianDTOResponse(entity.getId(), entity.getTechnicianName());
    }
    
    public static TechnicianEntity toEntity(TechnicianDTORequest dto) {
       
        return new TechnicianEntity(dto.technicianName());
    }
}
