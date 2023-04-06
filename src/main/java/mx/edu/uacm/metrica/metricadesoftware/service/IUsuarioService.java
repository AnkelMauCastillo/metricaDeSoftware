package mx.edu.uacm.metrica.metricadesoftware.service;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IUsuarioService {

    Usuario buscarPorId(long id);
    List<Usuario> buscarTodos();

    Usuario crearUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorCorreoYContrasenia(String email, String contrasenia);
}
