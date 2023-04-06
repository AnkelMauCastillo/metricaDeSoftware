package mx.edu.uacm.metrica.metricadesoftware.service;

import mx.edu.uacm.metrica.metricadesoftware.expcion.AplicacionExcepcion;
import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IHistoriaDeUsuarioService {

    HistoriaDeUsuario buscarPorId(Long id);
    HistoriaDeUsuario guardar(HistoriaDeUsuario historia) throws AplicacionExcepcion;;
    void eliminar(Long id);
    List<HistoriaDeUsuario> buscarTodos();
    List<HistoriaDeUsuario> buscarPorAsignado(Usuario usuario);

    Integer sumarPuntosHistorias();

}
