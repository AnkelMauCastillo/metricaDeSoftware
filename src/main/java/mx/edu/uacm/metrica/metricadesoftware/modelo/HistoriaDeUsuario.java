package mx.edu.uacm.metrica.metricadesoftware.modelo;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Entity
@Slf4j
public class HistoriaDeUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int points;
    private String status;

    private int cycleTime;
    private int leadTime;


    private LocalDate fechaCreacion;

    private LocalDate fechaFinalizacion;
    @ManyToOne
    @JoinColumn(name = "asignado_id")
    private Usuario asignado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Usuario getAsignado() {
        return asignado;
    }

    public void setAsignado(Usuario asignado) {
        this.asignado = asignado;
    }
    // getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCycleTime() {
        return cycleTime;
    }

    public void setCycleTime(int cycleTime) {
        this.cycleTime = cycleTime;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    @Override
    public String toString() {
        return "HistoriaDeUsuario{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", status='" + status + '\'' +
                ", cycleTime=" + cycleTime +
                ", leadTime=" + leadTime +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", asignado=" + asignado +
                ", sprint=" + sprint +
                '}';
    }
}
