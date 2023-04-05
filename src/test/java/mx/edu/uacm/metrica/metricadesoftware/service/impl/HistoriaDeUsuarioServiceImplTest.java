package mx.edu.uacm.metrica.metricadesoftware.service.impl;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.repository.HistoriaDeUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class HistoriaDeUsuarioServiceImplTest {


    @Autowired
    private HistoriaDeUsuarioRepository historiaDeUsuarioRepository;



    @Test
    public void buscarPorIdTest() {
        // Crear un objeto de prueba
        HistoriaDeUsuario historia = new HistoriaDeUsuario();
        historia.setTitle("Historia de usuario 1");
        historia.setDescription("Esta es la descripción de la historia de usuario 1");

        historiaDeUsuarioRepository.save(historia);


        // Ejecutar el método a probar
        HistoriaDeUsuarioServiceImpl historiaDeUsuarioService = new HistoriaDeUsuarioServiceImpl(historiaDeUsuarioRepository);
        HistoriaDeUsuario resultado = historiaDeUsuarioService.buscarPorId(historia.getId());

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(historia.getId(), resultado.getId());
        assertEquals("Historia de usuario 1", resultado.getTitle());
        assertEquals("Esta es la descripción de la historia de usuario 1", resultado.getDescription());
    }


}