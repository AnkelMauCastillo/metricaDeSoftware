package mx.edu.uacm.metrica.metricadesoftware.service.impl;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;

import java.time.temporal.ChronoUnit;

public class CalculadoraLeadTimeCycleTime {

    public static int calcularCycleTime(HistoriaDeUsuario historia) {
        return (int) ChronoUnit.DAYS.between(historia.getFechaCreacion(), historia.getFechaFinalizacion());
    }

    public static int calcularLeadTime(HistoriaDeUsuario historia) {
        Sprint sprint = historia.getSprint();
        return (int) ChronoUnit.DAYS.between(sprint.getFechaInicio(), historia.getFechaFinalizacion());
    }
}
