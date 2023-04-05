package mx.edu.uacm.metrica.metricadesoftware.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriaDeUsuario> historiasDeUsuario;

    public Sprint() {
    }

    public Sprint(LocalDate fechaInicio, LocalDate fechaFin, List<HistoriaDeUsuario> historiasDeUsuario) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.historiasDeUsuario = historiasDeUsuario;
    }

    // getters y setters
    // métodos de negocio

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<HistoriaDeUsuario> getHistoriasDeUsuario() {
        return historiasDeUsuario;
    }

    public void setHistoriasDeUsuario(List<HistoriaDeUsuario> historiasDeUsuario) {
        this.historiasDeUsuario = historiasDeUsuario;
    }
}
