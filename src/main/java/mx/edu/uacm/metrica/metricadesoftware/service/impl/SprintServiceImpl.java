package mx.edu.uacm.metrica.metricadesoftware.service.impl;

import mx.edu.uacm.metrica.metricadesoftware.expcion.ResourceNotFoundException;
import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;
import mx.edu.uacm.metrica.metricadesoftware.repository.SprintRepository;
import mx.edu.uacm.metrica.metricadesoftware.service.ISprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SprintServiceImpl implements ISprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Override
    public List<Sprint> obtenerTodosSprints() {
        return sprintRepository.findAll();
    }

    @Override
    public Sprint obtenerSprintPorId(Long id) {
        return sprintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sprint", "id", id));
    }

    @Override
    public Sprint guardarSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public void eliminarSprint(Long id) {
        Sprint sprint = sprintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sprint", "id", id));
        sprintRepository.delete(sprint);
    }

    @Override
    public Sprint agregarHistoriasDeUsuarioASprint(Long sprintId, List<HistoriaDeUsuario> historiasDeUsuario) {
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new ResourceNotFoundException("Sprint", "id", sprintId));
        sprint.getHistoriasDeUsuario().addAll(historiasDeUsuario);
        return sprintRepository.save(sprint);
    }


}
