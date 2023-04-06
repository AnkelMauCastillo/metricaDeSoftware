package mx.edu.uacm.metrica.metricadesoftware.modelo;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
     
    public int calcularDiasLaborables(LocalDate fechaInicio, LocalDate fechaFin) {
        int diasTotales = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);

        int diasNoLaborables = 0;
        LocalDate fechaActual = fechaInicio;
        while (fechaActual.isBefore(fechaFin)) {
            if (esDiaNoLaborable(fechaActual)) {
                diasNoLaborables++;
            }
            fechaActual = fechaActual.plusDays(1);
        }

        int diasLaborables = diasTotales - diasNoLaborables;
        return diasLaborables;
    }

    private boolean esDiaNoLaborable(LocalDate fecha) {
    	 // verificar si es fin de semana
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            return true;
        }

        return false;
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
