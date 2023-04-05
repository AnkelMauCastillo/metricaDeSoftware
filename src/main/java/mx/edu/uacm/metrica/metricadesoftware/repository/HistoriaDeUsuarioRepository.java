package mx.edu.uacm.metrica.metricadesoftware.repository;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriaDeUsuarioRepository extends JpaRepository<HistoriaDeUsuario, Long> {
    List<HistoriaDeUsuario> findByAsignado(Usuario usuario);
    List<HistoriaDeUsuario> findByStatus(String status);
    Page<HistoriaDeUsuario> findAll(Pageable pageable);
}
