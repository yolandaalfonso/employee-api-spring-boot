package dev.yolanda.employeeAPI.solicitud;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.yolanda.employeeAPI.implementations.IGenericService;

@Service
public class SolicitudServiceImpl implements IGenericService<SolicitudEntity>{

    private final SolicitudRepository repository;

    public SolicitudServiceImpl(SolicitudRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SolicitudEntity> getEntities() {
        return repository.findAll();
    }

    @Override
    public SolicitudEntity saveEntity(SolicitudEntity solicitud) {
        return repository.save(solicitud);
    }

}
