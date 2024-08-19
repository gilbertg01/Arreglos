package laboratorio.Empleados.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class adminController {

    @GetMapping("/index")
    public String adminHome() {
        return "/index";
    }
}
