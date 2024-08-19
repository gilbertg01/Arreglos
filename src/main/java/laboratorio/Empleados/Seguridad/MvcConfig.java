package laboratorio.Empleados.Seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/template/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/template/login").setViewName("login");
        registry.addViewController("/template/indexSecretaria").setViewName("indexSecretaria");
        registry.addViewController("/template/indexBionalista").setViewName("indexBionalista");
        registry.addViewController("/template/ARS").setViewName("ars");
        registry.addViewController("/template/Doctores").setViewName("doctores");
        registry.addViewController("/template/Empleados").setViewName("empleados");
        registry.addViewController("/template/Pacientes").setViewName("pacientes");
        registry.addViewController("/template/Pruebas").setViewName("pruebas");
        registry.addViewController("/template/Suplidores").setViewName("suplidores");
    }
}
