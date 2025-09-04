import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import dev.yolanda.employeeAPI.solicitud.SolicitudEntity;

public class SolicitudEntityTest {
    
    @Test
    void testSolicitudEntity_Initialization() {
        SolicitudEntity solicitud = new SolicitudEntity(
                1L,
                "Juan",
                LocalDate.of(2025, 7, 15),
                "vacaciones",
                "solicitud de días libres"
        );

        assertThat(solicitud, is(instanceOf(SolicitudEntity.class)));
        assertThat(solicitud.getClass().getDeclaredFields().length, is(equalTo(5)));

        assertThat(solicitud.getApplicantName(), is("Juan"));
        assertThat(solicitud.getSubject(), is("vacaciones"));
    }

    @Test
    void testSolicitudEntity_Setters() {
        SolicitudEntity solicitud = new SolicitudEntity();
        solicitud.setId(1L);
        solicitud.setApplicantName("Juan");
        solicitud.setApplicationDate(LocalDate.of(2025, 7, 15));
        solicitud.setSubject("vacaciones");
        solicitud.setDescription("solicitar días libres");

        assertThat(solicitud.getId(), is(equalTo(1L)));
        assertThat(solicitud.getApplicantName(), is(equalTo("Juan")));
        assertThat(solicitud.getApplicationDate(), is(equalTo(LocalDate.of(2025, 7, 15))));
        assertThat(solicitud.getSubject(), is(equalTo("vacaciones")));
        assertThat(solicitud.getDescription(), is(equalTo("solicitar días libres")));
    }

   /*  @Test
    void testCreateSolicitudEntityWithBuilder_Stategy2() {
        SolicitudEntity Juan = SolicitudEntity.builder()
                .id(1)
                .applicantName("Juan")
                .LocalDate(2025, 07, 15)
                .subject("vacaciones")
                .description("solicitud días")
                .build();
        
        assertThat(Juan, is(instanceOf(SolicitudEntity.class)));
        assertThat(Juan.getId(), is(equalTo(1)));
        assertThat(Juan.getApplicantName()(), is(equalTo("Juan")));
    } */
}
