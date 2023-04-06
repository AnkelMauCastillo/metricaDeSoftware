package mx.edu.uacm.metrica.metricadesoftware.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.service.IUsuarioService;

@Controller 
public class UsuarioController {
  @Autowired
  private IUsuarioService usuarioService;
  /*
   * listar los usuarios registrados para el selecc del registro de la historia de usuario
   * */ 
  @GetMapping("/historiaRegistro")
  public String mostrarUsuarios(Model model) {
	  List<Usuario>listaUsuarios = usuarioService.buscarTodos();
	  model.addAttribute("usuarios",listaUsuarios);
	  return "historiaRegistro";
  }
  
}
