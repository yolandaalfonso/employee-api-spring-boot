package dev.yolanda.employeeAPI.solicitud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.yolanda.employeeAPI.implementations.IGenericService;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;
import dev.yolanda.employeeAPI.solicitud.mappers.SolicitudMapper;

@Service
public class SolicitudServiceImpl implements IGenericService<SolicitudDTOResponse, SolicitudDTORequest>{

    private final SolicitudRepository repository;

    public SolicitudServiceImpl(SolicitudRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SolicitudDTOResponse> getEntities() {
         List<SolicitudDTOResponse> solicitudes = new ArrayList<>();

        repository.findAll().forEach(c -> {
            SolicitudDTOResponse solicitud = SolicitudMapper.toDTO(c);
            solicitudes.add(solicitud);
        });

        return solicitudes;
    }

    @Override
    public SolicitudDTOResponse storeEntity(SolicitudDTORequest solicitudDTORequest) {
        SolicitudEntity solicitud = SolicitudMapper.toEntity(solicitudDTORequest);
        SolicitudEntity solicitudStored = repository.save(solicitud);
        return SolicitudMapper.toDTO(solicitudStored) ;
    }

    @Override
    public List<SolicitudDTOResponse> getEntitiesOrdered() {
        return repository.findAllByOrderByApplicationDateAsc()
                         .stream()
                         .map(SolicitudMapper::toDTO)
                         .toList();
    }

    public List<SolicitudDTOResponse> getPendientes() {
        return repository.findByAtendidaFalseOrderByApplicationDateAsc()
                         .stream()
                         .map(SolicitudMapper::toDTO)
                         .toList();
    }

    @Override
    public SolicitudDTOResponse marcarComoAtendida(Long id, String tecnico) {
        SolicitudEntity solicitud = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id " + id));

        solicitud.setAtendida(true);
        solicitud.setAtendidoPor(tecnico);

        SolicitudEntity updated = repository.save(solicitud);
        return SolicitudMapper.toDTO(updated);
    }

    //@Override
    //public SolicitudDTOResponse showById(Long id) {
        //SolicitudEntity solicitud = repository.findById(id).orElseThrow(() -> new SolicitudExceptionNotFound("Solicitud no encontrada. Id " + id + " no existe."));
        //return SolicitudMapper.toDTO(solicitud);
    //}

}
