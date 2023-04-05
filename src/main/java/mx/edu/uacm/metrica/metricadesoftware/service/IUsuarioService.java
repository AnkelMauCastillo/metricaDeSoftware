package mx.edu.uacm.metrica.metricadesoftware.service;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario buscarPorId(long id);
    List<Usuario> buscarTodos();

    Usuario crearUsuario(Usuario usuario);
}
