package laboratorio.Resultado.Controladores;
import jakarta.websocket.server.PathParam;
import laboratorio.Email.Servicio.EmailService;
import laboratorio.Facturacion.Entidades.Factura;
import laboratorio.Facturacion.Entidades.FacturaDTO;
import laboratorio.Facturacion.Repositorios.FacturaRepository;
import laboratorio.Paciente.Entidades.Paciente;
import laboratorio.Pruebas.Entidades.Prueba;
import laboratorio.Pruebas.Entidades.PruebaDTO;
import laboratorio.Pruebas.Repositorios.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import laboratorio.Resultado.Entidades.*;
import laboratorio.Resultado.Repositorios.ResultadoRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoRestController {
    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private PruebaRepository pruebaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarResultado(@RequestBody ResultadoRequest resultadoRequest) {
        try {
            Optional<Factura> optionalFactura = facturaRepository.findById(resultadoRequest.getFacturaId());
            if (!optionalFactura.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Factura no encontrada"));
            }

            Optional<Prueba> optionalPrueba = pruebaRepository.findById(resultadoRequest.getPruebaId());
            if (!optionalPrueba.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Prueba no encontrada"));
            }

            Factura factura = optionalFactura.get();
            Prueba prueba = optionalPrueba.get();
            Paciente paciente = factura.getPaciente();

            Resultado resultado = new Resultado(factura, prueba, resultadoRequest.getResultadoTexto(), paciente);
            resultadoRepository.save(resultado);

            boolean todasPruebasConResultados = factura.getPruebas().stream()
                    .allMatch(p -> resultadoRepository.existsByPruebaAndFactura(p, factura));
            if (todasPruebasConResultados) {
                factura.setCompletada(true);
                facturaRepository.save(factura);
            }

            emailService.enviarCorreoResultado(paciente, prueba, resultado);

            List<PruebaDTO> pruebasDTO = factura.getPruebas().stream()
                    .map(p -> new PruebaDTO(p, resultadoRepository.existsByPruebaAndFactura(p, factura)))
                    .collect(Collectors.toList());

            List<FacturaDTO> facturasDTO = facturaRepository.findByCompletadaFalse().stream()
                    .map(FacturaDTO::new)
                    .collect(Collectors.toList());

            System.out.println("\nResultado procesado: \n" + resultado);
            return ResponseEntity.ok(Map.of("message", "Resultado registrado con éxito", "pruebas", pruebasDTO, "facturas", facturasDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Ocurrió un error inesperado"));
        }
    }
}






