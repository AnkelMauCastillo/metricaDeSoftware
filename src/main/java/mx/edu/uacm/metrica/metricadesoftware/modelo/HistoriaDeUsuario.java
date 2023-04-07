package mx.edu.uacm.metrica.metricadesoftware.modelo;

import jakarta.persistence.*;

@Entity
public class HistoriaDeUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int points;
    private String status;
    @ManyToOne
    @JoinColumn(name = "asignado_id")
    private Usuario asignado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

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

    @Override
    public String toString() {
        return "HistoriaDeUsuario{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", status='" + status + '\'' +
                ", asignado=" + asignado +
                '}';
    }
}
