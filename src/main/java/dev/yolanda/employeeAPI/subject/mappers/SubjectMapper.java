package dev.yolanda.employeeAPI.subject.mappers;

import org.springframework.stereotype.Component;

import dev.yolanda.employeeAPI.subject.SubjectEntity;
import dev.yolanda.employeeAPI.subject.dtos.SubjectDTORequest;
import dev.yolanda.employeeAPI.subject.dtos.SubjectDTOResponse;

@Component
public class SubjectMapper {

    public static SubjectEntity toEntity(SubjectDTORequest dtoRequest) {
        SubjectEntity subject = new SubjectEntity();
        subject.setName(dtoRequest.name());

        return subject;
    }

    public static SubjectDTOResponse toDTO(SubjectEntity entity) {
        SubjectDTOResponse dtoResponse = new SubjectDTOResponse(entity.getId(), entity.getName());

        return dtoResponse;
    }
}
