package mx.edu.uacm.metrica.metricadesoftware.service;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;

import java.util.List;

public interface ISprintService {

    List<Sprint> obtenerTodosSprints();
    Sprint obtenerSprintPorId(Long id);
    Sprint guardarSprint(Sprint sprint);
    void eliminarSprint(Long id);

    Sprint agregarHistoriasDeUsuarioASprint(Long sprintId, List<HistoriaDeUsuario> historiasDeUsuario);
}
