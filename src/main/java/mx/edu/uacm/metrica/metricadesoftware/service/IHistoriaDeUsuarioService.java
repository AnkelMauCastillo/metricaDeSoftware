package mx.edu.uacm.metrica.metricadesoftware.service;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;

import java.util.List;

public interface IHistoriaDeUsuarioService {

    HistoriaDeUsuario buscarPorId(Long id);
    void guardar(HistoriaDeUsuario historia);
    void eliminar(Long id);
    List<HistoriaDeUsuario> buscarTodos();
}
