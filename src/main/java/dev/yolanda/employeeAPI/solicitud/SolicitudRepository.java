package dev.yolanda.employeeAPI.solicitud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long>{
    List<SolicitudEntity> findAllByOrderByApplicationDateAsc();
}
