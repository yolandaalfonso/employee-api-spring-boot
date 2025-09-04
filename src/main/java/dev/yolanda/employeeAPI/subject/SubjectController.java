package dev.yolanda.employeeAPI.subject;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.yolanda.employeeAPI.implementations.IReadableService;
import dev.yolanda.employeeAPI.subject.dtos.SubjectDTOResponse;


@RestController
@RequestMapping("${api-endpoint}/subjects")
public class SubjectController {

    private final IReadableService<SubjectDTOResponse> service;

    public SubjectController(IReadableService<SubjectDTOResponse> service) {
        this.service = service; 
    }
    

    @GetMapping("")
    public List<SubjectDTOResponse> index() {
        return service.getEntities();
    }
}
