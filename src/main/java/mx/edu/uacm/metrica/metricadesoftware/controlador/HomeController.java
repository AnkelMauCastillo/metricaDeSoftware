package mx.edu.uacm.metrica.metricadesoftware.controlador;

import mx.edu.uacm.metrica.metricadesoftware.expcion.AplicacionExcepcion;
import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.CalculadoraLeadTimeCycleTime;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.HistoriaDeUsuarioServiceImpl;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.SprintServiceImpl;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    @Autowired
    private UsuarioServiceImpl usuarioService;


    
    @GetMapping("/historiasUsuario")
    public String mostrarInicio(Model model) {
        // Obtener todas las historias de usuario
        List<HistoriaDeUsuario> historias = historiaDeUsuarioService.buscarTodos();
        Integer puntosTotales = historiaDeUsuarioService.sumarPuntosHistorias();
        model.addAttribute("puntosTotales", puntosTotales);
        // Agregar las historias de usuario al modelo
        model.addAttribute("historias", historias);
        Sprint sprint = sprintService.obtenerSprintPorId(1L);
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();
        buscarDiasLaborables(model, fechaInicio, fechaFin);

        return "historiasUsuario";
    }
 
    
    @GetMapping("/kanban")
    public String mostrarKanban(Model model) {
        Sprint sprint = sprintService.obtenerSprintPorId(1L);
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();
        long duracion = sprint.calcularDiasLaborables(fechaInicio, fechaFin);
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

            switch (estado) {
                case "porhacer" -> historiasPorHacer.add(h);
                case "haciendo" -> historiasHaciendo.add(h);
                case "hecho" -> historiasHechas.add(h);
            }
        }

        model.addAttribute("puntosTotales", puntosTotales);
        model.addAttribute("HistoriasPorHacer", historiasPorHacer);
        model.addAttribute("HistoriasHaciendo", historiasHaciendo);
        model.addAttribute("HistoriasHechas", historiasHechas);
        model.addAttribute("idSprint", sprint.getId());

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
        Sprint sprint = sprintService.obtenerSprintPorId(1L);
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();
        long duracion = sprint.calcularDiasLaborables(fechaInicio, fechaFin);

        buscarDiasLaborables(model, fechaInicio, fechaFin);
        boolean banderaFecha = historiaDeUsuario.getStatus().equals("porhacer");
        System.out.println(banderaFecha);
        model.addAttribute("banderaFecha", banderaFecha);
        model.addAttribute("mapStatus", mapStatus);
        model.addAttribute("historiaDeUsuario", historiaDeUsuario);
        model.addAttribute("idSprint", sprint.getId());

        return "editar";

    }

    private void buscarDiasLaborables(Model model, LocalDate fechaInicio, LocalDate fechaFin) {
        List<LocalDate> diasLaborables = new ArrayList<>();
        for (LocalDate date = fechaInicio; date.isBefore(fechaFin); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                diasLaborables.add(date);
            }
        }

        model.addAttribute("diasLaborables", diasLaborables);
    }

    @GetMapping("/editarFecha/{idHistoria}")
    public String editarFecha(@PathVariable("idHistoria") Long idHistoria, Model model){
        Map<String, String> mapStatus = new HashMap<>();
        HistoriaDeUsuario historiaDeUsuario = historiaDeUsuarioService.buscarPorId(idHistoria);

        if (historiaDeUsuario.getStatus().equals("porhacer")) {
            mapStatus.put("porhacer", "Por Hacer");
            mapStatus.put("haciendo", "Haciendo");
        } else if (historiaDeUsuario.getStatus().equals("haciendo")) {
            mapStatus.put("hecho", "Hecho");
        }

        model.addAttribute("mapStatus", mapStatus);
        model.addAttribute("historiaDeUsuario", historiaDeUsuario);

        Sprint sprint = sprintService.obtenerSprintPorId(1L);
        model.addAttribute("idSprint", sprint.getId());
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();
        long duracion = sprint.calcularDiasLaborables(fechaInicio, fechaFin);

        buscarDiasLaborables(model, fechaInicio, fechaFin);


        return "editarFecha";

    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("historiaDeUsuario") HistoriaDeUsuario historiaDeUsuario
                          ) throws AplicacionExcepcion {

        if (historiaDeUsuario != null) {
            System.out.println("entro al modificar la historia");
            System.out.println(historiaDeUsuario);
            HistoriaDeUsuario revisar = historiaDeUsuarioService.guardar(historiaDeUsuario);
            System.out.println(revisar);
        } else {
            HistoriaDeUsuario revisar = historiaDeUsuarioService.guardar(historiaDeUsuario);
        }

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

        sprintService.guardarSprint(sprint);
        // Redirigir a la página de lista de sprints
        return "redirect:/";
    }

    @GetMapping("/otroControlador")
    public String otroControlador(@ModelAttribute("historiaDeUsuario") HistoriaDeUsuario historiaDeUsuario,
                          @RequestParam("fechaTermino") String fechaTermino,
                                  @RequestParam("idHistoria") Long idHistoria,
                                  @RequestParam("idSprint") Long idSprint) {
        // Actualiza la propiedad fechaTermino de la historia de usuario con la fecha seleccionada
        historiaDeUsuario = historiaDeUsuarioService.buscarPorId(idHistoria);
        Sprint sprint = sprintService.obtenerSprintPorId(idSprint);

        System.out.println("Entro");
        System.out.println(historiaDeUsuario);

        historiaDeUsuario.setFechaFinalizacion(LocalDate.parse(fechaTermino));
        historiaDeUsuario.setSprint(sprint);
        historiaDeUsuario.setSprint(sprint);


        try {
            historiaDeUsuarioService.guardar(historiaDeUsuario);
        } catch (AplicacionExcepcion e) {
            throw new RuntimeException(e);
        }

        // Guarda la historia de usuario en la base de datos o en algún otro lugar donde la estés almacenando


        // Redirige a la página que quieras mostrar después de guardar
        return "redirect:/kanban";
    }

    @GetMapping("/charts")
    public String mostrarGrafica(Model model){
        Sprint sprint = sprintService.obtenerSprintPorId(1L);
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();
        long duracion = sprint.calcularDiasLaborables(fechaInicio, fechaFin);
        List<HistoriaDeUsuario> historias = historiaDeUsuarioService.buscarTodos();
        Integer puntosTotales = historiaDeUsuarioService.sumarPuntosHistorias();
        //Map <String, Object> estimar = new HashMap<>();
        double m = (double) puntosTotales / duracion;
        int b = puntosTotales;
        List<Integer> lineaTendencia = new ArrayList<>();
        List<Integer> dias = new ArrayList<>();
        for (int i = 0; i <= duracion; i++) {
            int valorEsperado = (int) Math.round(b - m * i);
            lineaTendencia.add(valorEsperado);
            dias.add(i+1);
            System.out.println(i+1);
            System.out.println(valorEsperado);
        }
        model.addAttribute("dias", dias);
        model.addAttribute("lineaTendencia", lineaTendencia);
        System.out.println("Prueba dias laborables");
        List<LocalDate> diasLaborables = new ArrayList<>();
        for (LocalDate date = fechaInicio; date.isBefore(fechaFin); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                diasLaborables.add(date);
                System.out.println(date);
            }
        }
        System.out.println("Prueba historias Finalizadas");
        for (HistoriaDeUsuario history: historias){
            System.out.println(history.getFechaFinalizacion());
        }
        List<Integer> lineaReal = new ArrayList<>();

        System.out.println("Prueba matriz");
        int restar = puntosTotales;
        int sumaTotal = 0;
        String auxHistoria = "";
        List<String> fechas = new ArrayList<>();
        List<Integer> sumTotalTareasTerminadas = new ArrayList<>();
        for (LocalDate diasLaborable : diasLaborables) {
            int sumaPuntos = 0;
            int acum = 0;
            for (HistoriaDeUsuario historia : historias) {
                if (diasLaborable.equals(historia.getFechaFinalizacion())) {
                    //System.out.println(historias.get(j).getFechaFinalizacion());
                    sumaPuntos = sumaPuntos + historia.getPoints();
                    acum++;
                    auxHistoria = String.valueOf(historia.getFechaFinalizacion());

                }


            }
            //cambiar a lista
            fechas.add(auxHistoria);
            System.out.println(fechas);
            sumTotalTareasTerminadas.add(acum);

            System.out.println(sumaPuntos);
            lineaReal.add(restar = restar - sumaPuntos);

            sumaTotal = sumaTotal + sumaPuntos;
        }
        model.addAttribute("sumTotalTareasTerminadas", sumTotalTareasTerminadas);
        model.addAttribute("fechas", fechas);
        System.out.println("Comparación sumarPuntos" + sumaTotal);
        model.addAttribute("lineaReal", lineaReal);
        model.addAttribute("dias", dias);
        model.addAttribute("lineaTendencia", lineaTendencia);



        return "charts2";
    }

    @GetMapping("/prueba")
    public String calcularMetricas(Model model){
        CalculadoraLeadTimeCycleTime calculadoraMetricas = new CalculadoraLeadTimeCycleTime();
        List <HistoriaDeUsuario> listHistorias = historiaDeUsuarioService.buscarTodos();
        for (int i = 0; i < listHistorias.size(); i++) {
            int cycleTime = calculadoraMetricas.calcularCycleTime(listHistorias.get(i));
            int leadTime = calculadoraMetricas.calcularLeadTime(listHistorias.get(i));
            listHistorias.get(i).setCycleTime(cycleTime);
            listHistorias.get(i).setLeadTime(leadTime);
            try {
                historiaDeUsuarioService.guardar(listHistorias.get(i));
            } catch (AplicacionExcepcion e) {
                throw new RuntimeException(e);
            }

            //System.out.println("El leadTime es:" + leadTime + " , El CycleTime es: " + cycleTime);
            //System.out.println(listHistorias.get(i));
        }



        Sprint sprint = sprintService.obtenerSprintPorId(1L);
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();
        long duracion = sprint.calcularDiasLaborables(fechaInicio, fechaFin);

        List<LocalDate> diasLaborables = new ArrayList<>();
        for (LocalDate date = fechaInicio; date.isBefore(fechaFin); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                diasLaborables.add(date);
            }
        }
        Map <String, Integer> cycleTimeMap = new HashMap<>();
        Map <String, Integer> leadTimeMap = new HashMap<>();
        for (int i = 0; i < diasLaborables.size(); i++) {
            for (int j = 0; j < listHistorias.size(); j++) {
                if (diasLaborables.get(i).equals(listHistorias.get(j).getFechaFinalizacion())) {
                    //System.out.println(listHistorias.get(j).getCycleTime());
                    System.out.println("dia:"+ (i+1) + ", " + diasLaborables.get(i) + ", LeadTime: " + listHistorias.get(j).getLeadTime() + ", CyvleTime: " + listHistorias.get(j).getCycleTime());
                    cycleTimeMap.put("dia: " + (i+1), listHistorias.get(j).getCycleTime());
                    leadTimeMap.put("dia" +(i+1),listHistorias.get(j).getLeadTime());
                }

            }

        }

        model.addAttribute("cycleTimeMap", cycleTimeMap);
        model.addAttribute("leadTimeMap", leadTimeMap);
        model.addAttribute("diasLaborables", diasLaborables);

        return "prueba";
    }

    @GetMapping("/chart")
    public String showChart(Model model) {
        // Crear una lista de historias de usuario
        List<HistoriaDeUsuario> historias = historiaDeUsuarioService.buscarTodos();
        // Agregue las historias de usuario que desea incluir en el gráfico aquí
        Sprint sprint = sprintService.obtenerSprintPorId(1L);
        LocalDate fechaInicio = sprint.getFechaInicio();
        LocalDate fechaFin = sprint.getFechaFin();


        // Crear una lista de puntos de datos para el gráfico
        List<Object[]> chartData = new ArrayList<>();
        long duracion = sprint.calcularDiasLaborables(fechaInicio, fechaFin);
        List<LocalDate> diasLaborables = new ArrayList<>();
        for (LocalDate date = fechaInicio; date.isBefore(fechaFin); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                diasLaborables.add(date);
            }
        }


        for (int i = 0; i <  diasLaborables.size() ; i++) {
            for (int j =0; j < historias.size(); j++) {
                if (diasLaborables.get(i).equals(historias.get(j).getFechaFinalizacion())) {
                    int leadTime = CalculadoraLeadTimeCycleTime.calcularLeadTime(historias.get(j));
                    chartData.add(new Object[]{leadTime , i, historias.get(j).getId()});
                }



            }

        }

        // Pasar los datos del gráfico a la plantilla de Thymeleaf
        model.addAttribute("chartData", chartData);

        return "chart";
    }

    @GetMapping("/tareas")
    public String contarTareasPorUsuario(Model model) {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        List<Map<String, Object>> tareasTotales = new ArrayList<>(); // Lista de tareas global

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            List<Map<String, Object>> tareas = historiaDeUsuarioService.contarTareasPorUsuario(usuario);
            tareasTotales.addAll(tareas); // Agrega las tareas de este usuario a la lista global
        }
        List<String> nombres = new ArrayList<>();
        List<Integer> tareaHechas = new ArrayList<>();

        for (int i = 0; i<tareasTotales.size(); i++){
            nombres.add((String) tareasTotales.get(i).get("usuario"));
            int numeros = ((Number) tareasTotales.get(i).get("numTareas")).intValue();
            tareaHechas.add(numeros);


            System.out.println(numeros);
        }
        System.out.println(nombres);
        System.out.println(tareaHechas);

        model.addAttribute("nombres", nombres);
        model.addAttribute("tareaHechas", tareaHechas);

        model.addAttribute("tareas", tareasTotales); // Agrega la lista de tareas al modelo
        return "tareas";
    }










}
