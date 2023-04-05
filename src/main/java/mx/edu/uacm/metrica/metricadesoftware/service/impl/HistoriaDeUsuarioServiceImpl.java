package mx.edu.uacm.metrica.metricadesoftware.service.impl;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.repository.HistoriaDeUsuarioRepository;
import mx.edu.uacm.metrica.metricadesoftware.service.IHistoriaDeUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HistoriaDeUsuarioServiceImpl implements IHistoriaDeUsuarioService {
    @Autowired
    private HistoriaDeUsuarioRepository historiaDeUsuarioRepository;

    public HistoriaDeUsuarioServiceImpl(HistoriaDeUsuarioRepository historiaDeUsuarioRepository) {
        this.historiaDeUsuarioRepository = historiaDeUsuarioRepository;
    }

    @Override
    public HistoriaDeUsuario buscarPorId(Long id) {
        Optional<HistoriaDeUsuario> optional = historiaDeUsuarioRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void guardar(HistoriaDeUsuario historia) {
        historiaDeUsuarioRepository.save(historia);
    }

    @Override
    public void eliminar(Long id) {
        historiaDeUsuarioRepository.deleteById(id);
    }

    @Override
    public List<HistoriaDeUsuario> buscarTodos() {
        return historiaDeUsuarioRepository.findAll();
    }

    @Override
    public List<HistoriaDeUsuario> buscarPorAsignado(Usuario usuario) {
        return historiaDeUsuarioRepository.findByAsignado(usuario);
    }

    @Override
    public Integer sumarPuntosHistorias() {
        List<HistoriaDeUsuario> historias = historiaDeUsuarioRepository.findAll();
        Integer puntosTotales = 0;
        for (HistoriaDeUsuario historia : historias) {
            puntosTotales += historia.getPoints();
        }
        return puntosTotales;
    }


}
