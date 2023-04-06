package mx.edu.uacm.metrica.metricadesoftware.controlador;

import mx.edu.uacm.metrica.metricadesoftware.expcion.AplicacionExcepcion;
import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.HistoriaDeUsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Controller
@RequestMapping("/historia")
@Slf4j
public class HistoriaController {
	 @Autowired
	    private HistoriaDeUsuarioServiceImpl historiaDeUsuarioService;

	   
	    
	    
	    @GetMapping("/registro")
	    public String registrarHistroia(Model model, HistoriaDeUsuario historia ) {
	    	String registrar;
	    	if (log.isDebugEnabled()) {
	    	      log.debug(">Entrando a HistoriaController.registrarHistoria");
	    	      log.debug("historia {}", historia);
	    	    }
	    	if(historia.getTitle()!=null && historia.getAsignado()!=null) {
	    		try {
	    	        HistoriaDeUsuario historiaGuardada;
	    	        historiaGuardada = historiaDeUsuarioService.guardar(historia);
	    	        if (historiaGuardada != null && historiaGuardada.getId() != null)
	    	          model.addAttribute("mensajeExitoso", "Registro exitoso!" +historia.getTitle());
	    	      } catch (AplicacionExcepcion e) {
	    	        log.error(e.getMessage());
	    	        model.addAttribute("mensajeError", e.getMessage());
	    	      }
	    	      registrar = "historiaRegistro::#modalMensaje";
	    	    } else {
	    	      registrar = "redirect:/historiaRegistro";
	    	    }
	    	    return registrar;
	    }
}
