package dev.yolanda.employeeAPI.technician;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

public class TechnicianEntityTest {
    @Test
    void testNoArgsConstructorAndSetters() {
        
        TechnicianEntity technician = new TechnicianEntity();
        technician.setTechnicianName("Juan");

        assertThat(technician.getId()).isNull(); 
        assertThat(technician.getTechnicianName()).isEqualTo("Juan");
    }

    @Test
    void testTechnicianEntity() {
        TechnicianEntity technician = new TechnicianEntity();

        technician.setId(1L);
        technician.setTechnicianName("Raquel");

        assertThat(technician.getId(), is(equalTo(1L)));
        assertThat(technician.getTechnicianName(), is(equalTo("Raquel")));
    } 
}
