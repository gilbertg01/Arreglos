package laboratorio.Empleados.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Bionalista")
public class BionalistaController {

    @GetMapping("/indexBionalista")
    public String bionalistaHome() {
        return "/indexBionalista";
    }
}
