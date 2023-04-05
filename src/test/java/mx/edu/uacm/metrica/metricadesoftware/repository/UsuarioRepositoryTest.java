package mx.edu.uacm.metrica.metricadesoftware.repository;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import static org.assertj.core.api.Assertions.assertThat;

import mx.edu.uacm.metrica.metricadesoftware.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
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
        
        System.out.println(resultado);
    }



}