package laboratorio.Facturacion.Controladores;

import laboratorio.Doctores.Entidades.Doctores;
import laboratorio.Doctores.Repositorios.DoctoresRepository;
import laboratorio.Email.Servicio.EmailService;
import laboratorio.Facturacion.Entidades.*;
import laboratorio.Facturacion.Repositorios.FacturaRepository;
import laboratorio.Facturacion.Repositorios.MetodoPagoRepository;
import laboratorio.Paciente.Entidades.Paciente;
import laboratorio.Paciente.Repositorios.PacienteRepository;
import laboratorio.Pruebas.Entidades.Prueba;
import laboratorio.Pruebas.Entidades.PruebaDTO;
import laboratorio.Pruebas.Repositorios.PruebaRepository;
import laboratorio.Resultado.Repositorios.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private PruebaRepository pruebaRepository;

    @Autowired
    private DoctoresRepository doctorRepository;

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<?> createFactura(@RequestBody FacturaRequest facturaRequest) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(facturaRequest.getPacienteId());
        if (!optionalPaciente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado");
        }

        Paciente paciente = optionalPaciente.get();
        Factura factura = new Factura();
        factura.setPaciente(paciente);
        factura.setFechaEmision(new Date());

        List<Prueba> pruebas = facturaRequest.getPruebas().stream()
                .map(nombrePrueba -> pruebaRepository.findByNombrePrueba(nombrePrueba)
                        .orElseThrow(() -> new RuntimeException("Prueba no encontrada: " + nombrePrueba)))
                .collect(Collectors.toList());

        factura.setPruebas(pruebas);

        double subtotal = pruebas.stream().mapToDouble(Prueba::getCosto).sum();
        // Manejar el caso donde ARS puede ser null
        double descuento = 0;
        if (paciente.getArs() != null) {
            descuento = paciente.getArs().getDescuento();
        }
        double totalDescuento = subtotal * descuento;
        double total = subtotal - totalDescuento;
        factura.setTotal(total);

        MetodoPago metodoPago = metodoPagoRepository.findById(facturaRequest.getMetodoPagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));
        factura.setMetodoPago(metodoPago);

        if (facturaRequest.getDoctorId() != null) {
            Doctores doctor = doctorRepository.findById(facturaRequest.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
            paciente.setDoctores(doctor);
            doctor.getPacientes().add(paciente);
        }
        factura.setNumeroFactura("X-00");
        factura.setCompletada(false);

        Factura savedFactura = facturaRepository.save(factura);
        savedFactura.setNumeroFactura("X-00" + savedFactura.getId());

        paciente.getFacturas().add(savedFactura);
        pacienteRepository.save(paciente);

        emailService.enviarCorreoFactura(paciente, savedFactura, pruebas);
        System.out.println("\nFactura procesada: \n" + savedFactura);
        return ResponseEntity.ok(savedFactura);
    }

    @GetMapping("/noCompletadas")
    public ResponseEntity<List<FacturaDTO>> getFacturasNoCompletadas() {
        List<Factura> facturas = facturaRepository.findByCompletadaFalse();
        List<FacturaDTO> facturasDTO = facturas.stream().map(FacturaDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(facturasDTO);
    }

    @GetMapping("/{id}/pruebas")
    public ResponseEntity<List<PruebaDTO>> getPruebasByFacturaId(@PathVariable Long id) {
        Optional<Factura> facturaOpt = facturaRepository.findById(id);
        if (!facturaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Factura factura = facturaOpt.get();
        List<PruebaDTO> pruebasDTO = factura.getPruebas().stream()
                .filter(prueba -> !resultadoRepository.existsByPruebaAndFactura(prueba, factura))
                .map(prueba -> new PruebaDTO(prueba, resultadoRepository.existsByPruebaAndFactura(prueba, factura)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pruebasDTO);
    }


}
