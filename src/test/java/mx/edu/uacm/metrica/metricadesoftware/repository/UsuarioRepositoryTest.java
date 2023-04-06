package mx.edu.uacm.metrica.metricadesoftware.repository;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;
import mx.edu.uacm.metrica.metricadesoftware.modelo.SprintTest;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import mx.edu.uacm.metrica.metricadesoftware.service.impl.UsuarioServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Slf4j
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Disabled
    public void testCreateUser(){

        Usuario usuario = new Usuario("Angel Castillo", "angel@uacm.mx");
        Usuario savedUsuario = usuarioRepository.save(usuario);

        assertThat(savedUsuario.getId()).isGreaterThan(0);

    }

    @Test
    public void testBuscarPorid(){
        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioRepository);

        // Llamada al método buscarPorId y verificación de los resultados
        Usuario resultado = usuarioService.buscarPorId(2L);
        
        System.out.println("resultado:" +resultado);
    }
    
    @Test 
	public void calculaDias() {
		log.debug("Entrando a calculaDias");
		Sprint sprint = new Sprint();
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaProximaSemana = fechaActual.plusWeeks(2);
		int duracion = sprint.calcularDiasLaborables(fechaActual, fechaProximaSemana);
		
		System.out.println("Duracion:" + duracion);
	}


}