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

    public Usuario getAsignado() {
        return asignado;
    }

    public void setAsignado(Usuario asignado) {
        this.asignado = asignado;
    }
    // getters y setters
}
