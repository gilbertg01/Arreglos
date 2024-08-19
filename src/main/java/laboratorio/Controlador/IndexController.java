package laboratorio.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/ars")
    public String ars(Model model) {
        return "ARS";
    }

    @GetMapping("/doctores")
    public String doctores(Model model) {
        return "Doctores";
    }

    @GetMapping("/empleados")
    public String empleados(Model model) {
        return "Empleados";
    }

    @GetMapping("/pacientes")
    public String pacientes(Model model) {
        return "Pacientes";
    }

    @GetMapping("/pruebas")
    public String pruebas(Model model) {
        return "Pruebas";
    }

    @GetMapping("/suplidores")
    public String suplidores(Model model) {
        return "Suplidores";
    }

    @GetMapping("/indexSecretaria")
    public String indexSecretaria(Model model) {
        return "indexSecretaria";
    }

    @GetMapping("/indexBionalista")
    public String indexBioanalista(Model model) {
        return "indexBionalista";
    }
}
