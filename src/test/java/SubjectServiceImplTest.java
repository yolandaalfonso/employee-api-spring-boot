import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.yolanda.employeeAPI.subject.SubjectEntity;
import dev.yolanda.employeeAPI.subject.SubjectRepository;
import dev.yolanda.employeeAPI.subject.SubjectServiceImpl;
import dev.yolanda.employeeAPI.subject.dtos.SubjectDTOResponse;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceImplTest {

    @InjectMocks
    private SubjectServiceImpl subjectService;
    
    @Mock
    private SubjectRepository repository;
    
    @BeforeEach
    void setUp() {
        subjectService = new SubjectServiceImpl(repository);
    }
    
    @Test
    void testGetSubjects_ShouldReturnAllSubjects() {
        // Arrange - Crear datos mock
        List<SubjectEntity> subjectsMock = List.of(
                new SubjectEntity(1L, "Soporte Técnico"),
                new SubjectEntity(2L, "Consulta General"),
                new SubjectEntity(3L, "Reclamo"));
        
        when(repository.findAll()).thenReturn(subjectsMock);
        
        // Act - Ejecutar el método
        List<SubjectDTOResponse> subjects = subjectService.getEntities();
        
        // Assert - Verificar resultados
        assertThat(subjects.size(), is(equalTo(3)));
        assertThat(subjects.get(0).name(), is(equalTo("Soporte Técnico")));
        assertThat(subjects.get(1).name(), is(equalTo("Consulta General")));
        assertThat(subjects.get(2).name(), is(equalTo("Reclamo")));
        
        // Verificar que se llamó al repository
        verify(repository, times(1)).findAll();
    }
    
    @Test
    void testGetSubjects_ShouldReturnEmptyList_WhenNoSubjectsExist() {
        // Arrange - Lista vacía
        when(repository.findAll()).thenReturn(List.of());
        
        // Act
        List<SubjectDTOResponse> subjects = subjectService.getEntities();
        
        // Assert
        assertThat(subjects.size(), is(equalTo(0)));
        assertTrue(subjects.isEmpty());
        
        verify(repository, times(1)).findAll();
    }
    
    @Test
    void testGetSubjects_ShouldMapCorrectly() {
        // Arrange - Un solo elemento para verificar mapeo
        SubjectEntity subjectEntity = new SubjectEntity(10L, "Consulta Específica");
        when(repository.findAll()).thenReturn(List.of(subjectEntity));
        
        // Act
        List<SubjectDTOResponse> subjects = subjectService.getEntities();
        
        // Assert - Verificar que el mapeo sea correcto
        assertThat(subjects.size(), is(equalTo(1)));
        SubjectDTOResponse subject = subjects.get(0);
        assertThat(subject, is(instanceOf(SubjectDTOResponse.class)));
        assertThat(subject.id(), is(equalTo(10L)));
        assertThat(subject.name(), is(equalTo("Consulta Específica")));
    }
}
