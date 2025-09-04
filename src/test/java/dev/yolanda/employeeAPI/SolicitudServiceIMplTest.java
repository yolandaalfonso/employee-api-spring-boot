package dev.yolanda.employeeAPI;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.yolanda.employeeAPI.solicitud.SolicitudEntity;
import dev.yolanda.employeeAPI.solicitud.SolicitudRepository;
import dev.yolanda.employeeAPI.solicitud.SolicitudServiceImpl;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;
import dev.yolanda.employeeAPI.solicitud.mappers.SolicitudMapper;

public class SolicitudServiceIMplTest {
    
    private final SolicitudRepository repository = Mockito.mock(SolicitudRepository.class);
    private final SolicitudServiceImpl service = new SolicitudServiceImpl(repository);

    @Test
    void testGetEntitiesOrdered() {
        // Given
        SolicitudEntity s1 = new SolicitudEntity(1L, "Ana", LocalDate.of(2025, 1, 10), "vacaciones", "descanso");
        SolicitudEntity s2 = new SolicitudEntity(2L, "Luis", LocalDate.of(2025, 1, 5), "soporte", "ordenador roto");

        Mockito.when(repository.findAllByOrderByApplicationDateAsc()).thenReturn(List.of(s2, s1));

        // When
        List<SolicitudDTOResponse> result = service.getEntitiesOrdered();

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).applicantName()).isEqualTo("Luis"); // primero el más antiguo
        assertThat(result.get(1).applicantName()).isEqualTo("Ana");
    }

    @Test
    void testStoreEntity() {
        // Given
        SolicitudDTORequest dtoRequest = new SolicitudDTORequest("Juan", LocalDate.of(2025, 2, 1), "vacaciones", "pedir días");
        SolicitudEntity entity = SolicitudMapper.toEntity(dtoRequest);

        Mockito.when(repository.save(Mockito.any(SolicitudEntity.class))).thenReturn(entity);

        // When
        SolicitudDTOResponse result = service.storeEntity(dtoRequest);

        // Then
        assertThat(result.applicantName()).isEqualTo("Juan");
        assertThat(result.subject()).isEqualTo("vacaciones");
    }
}
