package mx.edu.uacm.metrica.metricadesoftware.service.impl;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.repository.UsuarioRepository;
import mx.edu.uacm.metrica.metricadesoftware.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario buscarPorId(long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElse(null);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
   
}
