package dev.yolanda.employeeAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import dev.yolanda.employeeAPI.implementations.IReadableService;
import dev.yolanda.employeeAPI.subject.SubjectController;
import dev.yolanda.employeeAPI.subject.dtos.SubjectDTOResponse;


@WebMvcTest(SubjectController.class)
@TestPropertySource(properties = "api-endpoint=/api")
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IReadableService<SubjectDTOResponse> service;

    @Test
    void testIndexEndpointReturnsEmptyList() throws Exception {
        when(service.getEntities()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/subjects"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testIndexEndpointReturnsSomeSubjects() throws Exception {
        SubjectDTOResponse subject = new SubjectDTOResponse("Math", "MATH101");
        when(service.getEntities()).thenReturn(Collections.singletonList(subject));

        mockMvc.perform(get("/api/subjects"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Math"))
               .andExpect(jsonPath("$[0].code").value("MATH101"));
    }
}
