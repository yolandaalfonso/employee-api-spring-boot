package dev.yolanda.employeeAPI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.yolanda.employeeAPI.exceptions.SolicitudNotFoundExceptions;
import dev.yolanda.employeeAPI.solicitud.SolicitudEntity;
import dev.yolanda.employeeAPI.solicitud.SolicitudRepository;
import dev.yolanda.employeeAPI.solicitud.SolicitudServiceImpl;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;
import dev.yolanda.employeeAPI.technician.TechnicianEntity;
import dev.yolanda.employeeAPI.technician.TechnicianRepository;

@ExtendWith(MockitoExtension.class)
public class SolicitudServiceImplTest {
    @InjectMocks
    private SolicitudServiceImpl solicitudService;

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private TechnicianRepository technicianRepository;

    @BeforeEach
    void setUp() {
        solicitudService = new SolicitudServiceImpl(solicitudRepository, technicianRepository);
    }

    @Test
    void testGetEntities_ShouldReturnAllSolicitudes() {
        List<SolicitudEntity> solicitudesMock = List.of(
                new SolicitudEntity(1L, "Juan", LocalDate.now(), "IT", "Fallo de red"),
                new SolicitudEntity(2L, "Ana", LocalDate.now(), "Soporte", "Error PC"));

        when(solicitudRepository.findAll()).thenReturn(solicitudesMock);
        List<SolicitudDTOResponse> solicitudes = solicitudService.getEntities();

        assertThat(solicitudes.size(), is(equalTo(2)));
        assertThat(solicitudes.get(0).applicantName(), is(equalTo("Juan")));
        assertThat(solicitudes.get(1).applicantName(), is(equalTo("Ana")));
    }

    @Test
    void testStoreEntity_ShouldReturnSolicitudStored() {
        SolicitudDTORequest request = new SolicitudDTORequest(
                "Luis", LocalDate.now(), "IT", "Pantalla rota", null);

        SolicitudEntity entity = new SolicitudEntity(1L, "Luis", request.applicationDate(),
                                                     request.subject(), request.description());

        when(solicitudRepository.save(any(SolicitudEntity.class))).thenReturn(entity);
        SolicitudDTOResponse stored = solicitudService.storeEntity(request);

        assertThat(stored.applicantName(), is(equalTo("Luis")));
        assertThat(stored.subject(), is(equalTo("IT")));
    }

    @Test
void testShowById_ShouldReturnSolicitud() {
    SolicitudEntity entity = new SolicitudEntity();
    entity.setId(1L);
    entity.setApplicantName("Carlos");
    entity.setApplicationDate(LocalDate.now());
    entity.setSubject("Red");
    entity.setDescription("Problema de conexión");

    SolicitudDTOResponse dto = new SolicitudDTOResponse(
        1L,
        "Carlos",
        entity.getApplicationDate(),
        "Red",
        "Problema de conexión",
        false,
        null,
        null,
        null
    );

    when(solicitudRepository.findById(1L)).thenReturn(Optional.of(entity));

    SolicitudDTOResponse result = solicitudService.showById(1L);

    assertThat(result, is(instanceOf(SolicitudDTOResponse.class)));
    assertThat(result.id(), is(equalTo(dto.id())));
    assertThat(result.applicantName(), is(equalTo(dto.applicantName())));
    assertThat(result.subject(), is(equalTo(dto.subject())));
    assertThat(result.description(), is(equalTo(dto.description())));
    }

    @Test
    void testShowById_ShouldThrowException_WhenNotFound() {
        when(solicitudRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        SolicitudNotFoundExceptions exception = assertThrows(
                SolicitudNotFoundExceptions.class,
                () -> solicitudService.showById(99L)
        );

        assertThat(exception.getMessage(), is(equalTo("Solicitud no encontrada. Id 99 no existe.")));
    }

    @Test
    void testMarcarComoAtendida_ShouldAssignTechnicianAndFechaAtencion() {
        SolicitudEntity solicitud = new SolicitudEntity(1L, "Pepe", LocalDate.now(), "Soporte", "No arranca");
        TechnicianEntity tecnico = new TechnicianEntity(1L, "Pedro");

        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(technicianRepository.findById(1L)).thenReturn(Optional.of(tecnico));
        when(solicitudRepository.save(any(SolicitudEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        SolicitudDTOResponse result = solicitudService.marcarComoAtendida(1L, 1L);

        assertThat(result.atendida(), is(true));
        assertThat(result.technicianName(), is(equalTo("Pedro")));
        assertThat(result.fechaAtencion().getClass(), is(equalTo(LocalDateTime.now().getClass())));
    }

    @Test
    void testMarcarComoAtendida_ShouldThrow_WhenSolicitudNotFound() {
        when(solicitudRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SolicitudNotFoundExceptions.class, () -> solicitudService.marcarComoAtendida(1L, 1L));
    }

    @Test
    void testMarcarComoAtendida_ShouldThrow_WhenTechnicianNotFound() {
        SolicitudEntity solicitud = new SolicitudEntity(1L, "Maria", LocalDate.now(), "Red", "No conecta");
        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        when(technicianRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> solicitudService.marcarComoAtendida(1L, 1L));
        assertThat(ex.getMessage(), is(equalTo("Técnico no encontrado")));
    }
}
