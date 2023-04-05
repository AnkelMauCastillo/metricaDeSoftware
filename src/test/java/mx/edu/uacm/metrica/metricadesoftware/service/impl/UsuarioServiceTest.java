package mx.edu.uacm.metrica.metricadesoftware.service.impl;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UsuarioServiceTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testBuscarPorId() {
        // Configuración del objeto Usuario y del repositorio real
        Usuario usuario = new Usuario();
        usuario.setNombre("persona");
        usuario.setEmail(usuario.getNombre()+"@uacm.com");

        usuarioRepository.save(usuario);

        UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuarioRepository);

        // Llamada al método buscarPorId y verificación de los resultados
        Usuario resultado = usuarioService.buscarPorId(usuario.getId());
        assertNotNull(resultado);
        assertEquals("persona", resultado.getNombre());
        assertEquals(usuario.getNombre()+"@uacm.com", resultado.getEmail());
    }

}
