package dev.yolanda.employeeAPI.subject;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.yolanda.employeeAPI.subject.SubjectEntity;


@ExtendWith(MockitoExtension.class)
public class SubjectEntityTest {
    @Test
    void testSubjectEntity_ShouldCreateEntityWithDefaultConstructor() {
        // Act
        SubjectEntity subject = new SubjectEntity();
        
        // Assert
        assertThat(subject, is(instanceOf(SubjectEntity.class)));
        assertThat(subject.getId(), is(nullValue()));
        assertThat(subject.getName(), is(nullValue()));
    }
    
    @Test
    void testSubjectEntity_ShouldCreateEntityWithNameConstructor() {
        // Arrange
        String expectedName = "Soporte Técnico";
        
        // Act
        SubjectEntity subject = new SubjectEntity(expectedName);
        
        // Assert
        assertThat(subject, is(instanceOf(SubjectEntity.class)));
        assertThat(subject.getName(), is(equalTo(expectedName)));
        assertThat(subject.getId(), is(nullValue())); // ID será null hasta persistir
    }
    
    @Test
    void testSettersAndGetters_ShouldWorkCorrectly() {
        // Arrange
        SubjectEntity subject = new SubjectEntity();
        Long expectedId = 10L;
        String expectedName = "Consulta General";
        
        // Act
        subject.setId(expectedId);
        subject.setName(expectedName);
        
        // Assert
        assertThat(subject.getId(), is(equalTo(expectedId)));
        assertThat(subject.getName(), is(equalTo(expectedName)));
    }
    
    @Test
    void testName_ShouldAcceptNullValue() {
        // Arrange & Act
        SubjectEntity subject = new SubjectEntity();
        subject.setName(null);
        
        // Assert
        assertThat(subject.getName(), is(nullValue()));
    }
    
    @Test
    void testName_ShouldAcceptEmptyString() {
        // Arrange & Act
        SubjectEntity subject = new SubjectEntity("");
        
        // Assert
        assertThat(subject.getName(), is(equalTo("")));
    }
    
    @Test
    void testId_ShouldAcceptNullValue() {
        // Arrange & Act
        SubjectEntity subject = new SubjectEntity("Test");
        subject.setId(null);
        
        // Assert
        assertThat(subject.getId(), is(nullValue()));
    }
    
    @Test
    void testId_ShouldAcceptLongValue() {
        // Arrange
        SubjectEntity subject = new SubjectEntity();
        Long expectedId = 999L;
        
        // Act
        subject.setId(expectedId);
        
        // Assert
        assertThat(subject.getId(), is(equalTo(expectedId)));
    }
    
    @Test
    void testEntity_ShouldMaintainStateCorrectly() {
        // Arrange
        SubjectEntity subject = new SubjectEntity("Initial Name");
        
        // Act - Cambiar valores
        subject.setId(1L);
        subject.setName("Updated Name");
        
        // Assert - Verificar que mantiene el estado actualizado
        assertThat(subject.getId(), is(equalTo(1L)));
        assertThat(subject.getName(), is(equalTo("Updated Name")));
    }
    
    @Test
    void testMultipleInstances_ShouldBeIndependent() {
        // Arrange & Act
        SubjectEntity subject1 = new SubjectEntity("Subject 1");
        SubjectEntity subject2 = new SubjectEntity("Subject 2");
        
        subject1.setId(1L);
        subject2.setId(2L);
        
        // Assert - Verificar que son independientes
        assertThat(subject1.getName(), is(equalTo("Subject 1")));
        assertThat(subject2.getName(), is(equalTo("Subject 2")));
        assertThat(subject1.getId(), is(equalTo(1L)));
        assertThat(subject2.getId(), is(equalTo(2L)));
        
        // Cambiar uno no afecta al otro
        subject1.setName("Modified Subject 1");
        assertThat(subject2.getName(), is(equalTo("Subject 2"))); // Sin cambios
    }
}
