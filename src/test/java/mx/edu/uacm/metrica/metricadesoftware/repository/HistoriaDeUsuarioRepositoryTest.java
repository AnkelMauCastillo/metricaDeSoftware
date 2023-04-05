package mx.edu.uacm.metrica.metricadesoftware.repository;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

class HistoriaDeUsuarioRepositoryTest {

    @Autowired
    private HistoriaDeUsuarioRepository historiaDeUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindByAsignado() {
        // Crear un usuario y una historia de usuario asignada a ese usuario
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");
        usuarioRepository.save(usuario);

        HistoriaDeUsuario historia = new HistoriaDeUsuario();
        historia.setTitle("Historia de usuario asignada a Juan");
        historia.setAsignado(usuario);
        historiaDeUsuarioRepository.save(historia);

        // Buscar historias de usuario asignadas a ese usuario
        List<HistoriaDeUsuario> historias = historiaDeUsuarioRepository.findByAsignado(usuario);

        // Verificar el resultado
        assertEquals(1, historias.size());
        assertEquals("Historia de usuario asignada a Juan", historias.get(0).getTitle());
    }
}