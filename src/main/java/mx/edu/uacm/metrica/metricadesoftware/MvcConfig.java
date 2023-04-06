package mx.edu.uacm.metrica.metricadesoftware;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	
	public void addViewControllers(ViewControllerRegistry registry){
		
		 registry.addViewController("/").setViewName("index");
		 registry.addViewController("/login").setViewName("login");
		 registry.addViewController("/register").setViewName("register");
		 registry.addViewController("/tables").setViewName("tables");
		 registry.addViewController("/charts").setViewName("charts");
		 registry.addViewController("/historiasUsuario").setViewName("historiasUsuario");
	}

}
