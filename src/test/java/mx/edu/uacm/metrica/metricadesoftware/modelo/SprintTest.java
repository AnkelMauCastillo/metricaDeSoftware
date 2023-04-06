package mx.edu.uacm.metrica.metricadesoftware.modelo;

import java.time.LocalDate;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.metrica.metricadesoftware.MetricaDeSoftwareApplication;

@SpringBootTest(classes = {MetricaDeSoftwareApplication.class})
@Slf4j
public class SprintTest {
	
	@Test 
	public void calculaDias() {
		log.debug("Entrando a calculaDias");
		Sprint sprint = new Sprint();
		
		LocalDate fechaActual = LocalDate.now();
		sprint.setFechaFin(fechaActual);
		LocalDate fechaProximaSemana = fechaActual.plusWeeks(1);
		int duracion = sprint.calcularDiasLaborables(fechaActual, fechaProximaSemana);
		assertNotNull(sprint.getFechaInicio());
		log.debug("duracion: "+duracion);
	}

}
