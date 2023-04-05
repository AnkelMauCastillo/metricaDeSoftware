package mx.edu.uacm.metrica.metricadesoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("mx.edu.uacm.metrica.metricadesoftware.modelo")
public class MetricaDeSoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricaDeSoftwareApplication.class, args);
    }

}
