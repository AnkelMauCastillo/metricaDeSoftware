package mx.edu.uacm.metrica.metricadesoftware.controlador;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.service.IUsuarioService;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.HistoriaDeUsuarioServiceImpl;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.SprintServiceImpl;

@Controller 
public class UsuarioController {
  @Autowired
  private IUsuarioService usuarioService;
  
  @Autowired
  private SprintServiceImpl sprintService;
  @Autowired
  HistoriaDeUsuarioServiceImpl historiaDeUsuarioService;
  /*
   * listar los usuarios registrados para el selecc del registro de la historia de usuario
   * */ 
  @GetMapping("/historiaRegistro")
  public String mostrarUsuarios(Model model) {
	  List<Usuario>listaUsuarios = usuarioService.buscarTodos();
	  List<Sprint> listSprints = sprintService.obtenerTodosSprints();
      model.addAttribute("listSprints", listSprints);
      model.addAttribute("usuarios",listaUsuarios);
	  return "historiaRegistro";
  }
  
  @GetMapping("/")
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
     
      }
      model.addAttribute("dias", dias);
      model.addAttribute("lineaTendencia", lineaTendencia);
      
      List<LocalDate> diasLaborables = new ArrayList<>();
      for (LocalDate date = fechaInicio; date.isBefore(fechaFin); date = date.plusDays(1)) {
          DayOfWeek dayOfWeek = date.getDayOfWeek();
          if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
              diasLaborables.add(date);
             
          }
      }
     
    
      List<Integer> lineaReal = new ArrayList<>();
     
      int restar = puntosTotales;
      int sumaTotal = 0;
      for (LocalDate diasLaborable : diasLaborables) {
          int sumaPuntos = 0;
          for (HistoriaDeUsuario historia : historias) {
              if (diasLaborable.equals(historia.getFechaFinalizacion())) {
                  //System.out.println(historias.get(j).getFechaFinalizacion());
                  sumaPuntos = sumaPuntos + historia.getPoints();


              }

          }
        
          lineaReal.add(restar = restar - sumaPuntos);

          sumaTotal = sumaTotal + sumaPuntos;
      }
     
      model.addAttribute("lineaReal", lineaReal);
      model.addAttribute("dias", dias);
      model.addAttribute("lineaTendencia", lineaTendencia);



      return "index";
  }

}
