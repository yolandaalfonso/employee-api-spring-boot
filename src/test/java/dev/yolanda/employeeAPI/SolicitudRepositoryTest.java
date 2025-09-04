package dev.yolanda.employeeAPI;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dev.yolanda.employeeAPI.solicitud.SolicitudEntity;
import dev.yolanda.employeeAPI.solicitud.SolicitudRepository;

public class SolicitudRepositoryTest {

    @Autowired
    private SolicitudRepository repository;
    
    @Test
    void testFindAllByOrderByApplicationDateAsc() {
        SolicitudEntity s1 = new SolicitudEntity(1L, "Ana", LocalDate.of(2025, 1, 10), "vacaciones", "descanso");
        SolicitudEntity s2 = new SolicitudEntity(2L, "Luis", LocalDate.of(2025, 1, 5), "soporte", "ordenador roto");

        repository.save(s1);
        repository.save(s2);

        List<SolicitudEntity> result = repository.findAllByOrderByApplicationDateAsc();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getApplicantName()).isEqualTo("Luis"); // el más antiguo
    }
}
