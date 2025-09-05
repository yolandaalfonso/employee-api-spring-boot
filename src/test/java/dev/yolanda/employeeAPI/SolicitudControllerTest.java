package dev.yolanda.employeeAPI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.yolanda.employeeAPI.solicitud.InterfaceSolicitudService;
import dev.yolanda.employeeAPI.solicitud.SolicitudController;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTORequest;
import dev.yolanda.employeeAPI.solicitud.dtos.SolicitudDTOResponse;

@WebMvcTest(controllers = SolicitudController.class)
public class SolicitudControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterfaceSolicitudService solicitudService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnSolicitudes() throws Exception {
        SolicitudDTOResponse s1 = new SolicitudDTOResponse(1L, "Carlos", LocalDate.now(), "Red", "Corte de internet", false, null, null, null);
        SolicitudDTOResponse s2 = new SolicitudDTOResponse(2L, "Ana", LocalDate.now(), "Software", "Error aplicación", false, null, null, null);
        List<SolicitudDTOResponse> solicitudes = List.of(s1, s2);
        String json = mapper.writeValueAsString(solicitudes);

        when(solicitudService.getEntities()).thenReturn(solicitudes);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/solicitudes"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test
    void testStore_ShouldReturn201_WhenValid() throws Exception {
        SolicitudDTORequest dto = new SolicitudDTORequest("Luis", LocalDate.now(), "IT", "Problema de red", 1L);
        SolicitudDTOResponse responseDTO = new SolicitudDTOResponse(1L, "Luis", LocalDate.now(), "IT", "Problema de red", false, null, null, null);

        when(solicitudService.storeEntity(dto)).thenReturn(responseDTO);

        String json = mapper.writeValueAsString(dto);
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/solicitudes").content(json).contentType("application/json"))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(), containsString("Luis"));
    }

    @Test
    void testStore_ShouldReturn400_WhenApplicantNameIsEmpty() throws Exception {
        SolicitudDTORequest dto = new SolicitudDTORequest("", LocalDate.now(), "IT", "Problema", 1L);
        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/v1/solicitudes").content(json).contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testStore_ShouldReturn204_WhenServiceReturnsNull() throws Exception {
        SolicitudDTORequest dto = new SolicitudDTORequest("Carlos", LocalDate.now(), "Red", "Error", 1L);
        String json = mapper.writeValueAsString(dto);

        when(solicitudService.storeEntity(dto)).thenReturn(null);

        mockMvc.perform(post("/api/v1/solicitudes").content(json).contentType("application/json"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testMarcarComoAtendida_ShouldReturn200() throws Exception {
        Long solicitudId = 1L;
        Long tecnicoId = 2L;
        SolicitudDTOResponse responseDTO = new SolicitudDTOResponse(1L, "Carlos", LocalDate.now(), "Red", "Corte de internet", true, "Pedro", LocalDateTime.now(), null);

        when(solicitudService.marcarComoAtendida(solicitudId, tecnicoId)).thenReturn(responseDTO);

        MockHttpServletResponse response = mockMvc.perform(
                put("/api/v1/solicitudes/{id}/atender?id=" + solicitudId, solicitudId)
                        .param("technicianId", tecnicoId.toString()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(), containsString("Pedro"));
    }

    @Test
    void testShow_ShouldReturn200_WhenFound() throws Exception {
        SolicitudDTOResponse responseDTO = new SolicitudDTOResponse(1L, "Carlos", LocalDate.now(), "Red", "Error", false, null, null, null);
        when(solicitudService.showById(1L)).thenReturn(responseDTO);

        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/solicitudes/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(), containsString("Carlos"));
    }
}
