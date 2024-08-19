package laboratorio.Facturacion.Controladores;

import laboratorio.Facturacion.Entidades.MetodoPago;
import laboratorio.Facturacion.Repositorios.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> getAllMetodosPago() {
        List<MetodoPago> metodosPago = metodoPagoRepository.findAll();
        return ResponseEntity.ok(metodosPago);
    }
    @PostMapping
    public ResponseEntity<MetodoPago> createMetodoPago(@RequestBody MetodoPago metodoPago) {
        MetodoPago createdMetodoPago = metodoPagoRepository.save(metodoPago);
        return ResponseEntity.ok(createdMetodoPago);
    }
}
