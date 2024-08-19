package laboratorio.Empleados.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Secretaria")
public class SecretariaController {

    @GetMapping("/indexSecretaria")
    public String secretariaHome() {
        return "/indexSecretaria";
    }
}
