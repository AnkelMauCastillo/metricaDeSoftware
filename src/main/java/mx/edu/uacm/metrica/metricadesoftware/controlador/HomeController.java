package mx.edu.uacm.metrica.metricadesoftware.controlador;

import mx.edu.uacm.metrica.metricadesoftware.modelo.HistoriaDeUsuario;
import mx.edu.uacm.metrica.metricadesoftware.service.IHistoriaDeUsuarioService;
import mx.edu.uacm.metrica.metricadesoftware.service.impl.HistoriaDeUsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HistoriaDeUsuarioServiceImpl historiaDeUsuarioService;

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        // Obtener todas las historias de usuario
        List<HistoriaDeUsuario> historias = historiaDeUsuarioService.buscarTodos();
        Integer puntosTotales = historiaDeUsuarioService.sumarPuntosHistorias();
        model.addAttribute("puntosTotales", puntosTotales);
        // Agregar las historias de usuario al modelo
        model.addAttribute("historias", historias);

        return "index";
    }

}
