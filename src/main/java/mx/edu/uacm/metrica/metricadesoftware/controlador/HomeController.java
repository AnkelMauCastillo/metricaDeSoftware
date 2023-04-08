package mx.edu.uacm.metrica.metricadesoftware.controlador;

import mx.edu.uacm.metrica.metricadesoftware.expcion.AplicacionExcepcion;
import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.service.IHistoriaDeUsuarioService;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.HistoriaDeUsuarioServiceImpl;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.SprintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private HistoriaDeUsuarioServiceImpl historiaDeUsuarioService;

    @Autowired
    private SprintServiceImpl sprintService;
    
    @GetMapping("/historiasUsuario")
    public String mostrarInicio(Model model) {
        // Obtener todas las historias de usuario
        List<HistoriaDeUsuario> historias = historiaDeUsuarioService.buscarTodos();
        Integer puntosTotales = historiaDeUsuarioService.sumarPuntosHistorias();
        model.addAttribute("puntosTotales", puntosTotales);
        // Agregar las historias de usuario al modelo
        model.addAttribute("historias", historias);

        return "historiasUsuario";
    }
 
    
    @GetMapping("/kanban")
    public String mostrarKanban(Model model) {
        Sprint sprint = sprintService.obtenerSprintPorId(1l);
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();
        long duracion = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);
        model.addAttribute("duracion", duracion);
        // Obtener todas las historias de usuario
        List<HistoriaDeUsuario> historias = historiaDeUsuarioService.buscarTodos();
        Integer puntosTotales = historiaDeUsuarioService.sumarPuntosHistorias();
        List<HistoriaDeUsuario> historiasPorHacer = new ArrayList<>();
        List<HistoriaDeUsuario> historiasHaciendo = new ArrayList<>();
        List<HistoriaDeUsuario> historiasHechas = new ArrayList<>();
        
        for (HistoriaDeUsuario h : historias) {
            String estado = h.getStatus();
            
            if (estado.equals("porhacer")) {
                historiasPorHacer.add(h);
            }
            else if (estado.equals("haciendo")) {
                historiasHaciendo.add(h);
            }
            else if (estado.equals("hecho")) {
                historiasHechas.add(h);
            }
        }

        model.addAttribute("puntosTotales", puntosTotales);
        model.addAttribute("HistoriasPorHacer", historiasPorHacer);
        model.addAttribute("HistoriasHaciendo", historiasHaciendo);
        model.addAttribute("HistoriasHechas", historiasHechas);

        return "kanban";
    }
    @GetMapping("/editar/{idHistoria}")
    public String editar(@PathVariable("idHistoria") Long idHistoria, Model model){
        Map<String, String> mapStatus = new HashMap<>();
        HistoriaDeUsuario historiaDeUsuario = historiaDeUsuarioService.buscarPorId(idHistoria);
        System.out.println(historiaDeUsuario);
        if (historiaDeUsuario.getStatus().equals("porhacer")) {
            mapStatus.put("porhacer", "Por Hacer");
            mapStatus.put("haciendo", "Haciendo");
        } else if (historiaDeUsuario.getStatus().equals("haciendo")) {
            mapStatus.put("hecho", "Hecho");
        }

        model.addAttribute("mapStatus", mapStatus);
        model.addAttribute("historiaDeUsuario", historiaDeUsuario);

        return "editar";

    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("historiaDeUsuario") HistoriaDeUsuario historiaDeUsuario) throws AplicacionExcepcion {
        HistoriaDeUsuario revisar = historiaDeUsuarioService.guardar(historiaDeUsuario);
        System.out.println(revisar);
        return "redirect:/kanban";
    }

    @PostMapping("/sprint/nuevo")
    public String crearSprint(@RequestParam("fechaInicio") @DateTimeFormat(pattern="dd/MM/yyyy") LocalDate fechaInicio,
                              @RequestParam("fechaFin") @DateTimeFormat(pattern="dd/MM/yyyy") LocalDate fechaFin
            ) {
        // Crear el objeto Sprint con las fechas y otros datos
        Sprint sprint = new Sprint(fechaInicio, fechaFin,historiaDeUsuarioService.buscarTodos());
        System.out.println(historiaDeUsuarioService.buscarTodos());
        System.out.println(sprint.getHistoriasDeUsuario());
        List<HistoriaDeUsuario> historiasDeUsuario = historiaDeUsuarioService.buscarTodos();

        // Establece la relación entre el sprint y las historias de usuario
        for (HistoriaDeUsuario historia : historiasDeUsuario) {
            historia.setSprint(sprint);
        }

        sprint.setHistoriasDeUsuario(historiasDeUsuario);
        sprintService.guardarSprint(sprint);
        // Redirigir a la página de lista de sprints
        return "redirect:/";
    }






}
