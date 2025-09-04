package dev.yolanda.employeeAPI.subject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.yolanda.employeeAPI.implementations.IReadableService;
import dev.yolanda.employeeAPI.subject.dtos.SubjectDTOResponse;
import dev.yolanda.employeeAPI.subject.mappers.SubjectMapper;


@Service
public class SubjectServiceImpl implements IReadableService<SubjectDTOResponse>{
    
    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubjectDTOResponse> getEntities() {
         List<SubjectDTOResponse> subjects = new ArrayList<>();

        repository.findAll().forEach(c -> {
            SubjectDTOResponse subject = SubjectMapper.toDTO(c);
            subjects.add(subject);
        });

        return subjects;
    }

}
