package mx.edu.uacm.metrica.metricadesoftware.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import mx.edu.uacm.metrica.metricadesoftware.expcion.AplicacionExcepcion;
import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Sprint;
import mx.edu.uacm.metrica.metricadesoftware.modelo.Usuario;
import mx.edu.uacm.metrica.metricadesoftware.service.IUsuarioService;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.HistoriaDeUsuarioServiceImpl;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.SprintServiceImpl;


@Controller
@RequestMapping("/usuario")
@Slf4j 
public class UsuarioController2 {
  @Autowired
  private IUsuarioService usuarioService;
  
  

  @GetMapping("/registro")
  public String registrarHistroia(Model model, Usuario usuario ) {
  	String registrar;
  	
  	if(usuario.getNombre()!=null && usuario.getEmail()!=null) {
  		Usuario usuarioGuardado;
		usuarioGuardado = usuarioService.crearUsuario(usuario);
		if (usuarioGuardado != null && usuarioGuardado.getId() != null)
		  model.addAttribute("mensajeExitoso", "Registro exitoso del usuario: " +usuario.getNombre());
  	      registrar = "register::#modalMensaje";
  	    } else {
  	      registrar = "redirect:/register";
  	    }
  	    return registrar;
  }
}
