package laboratorio.Paciente.Controlador;

import laboratorio.ARS.Entidades.ARS;
import laboratorio.ARS.Repositorio.ARSRepository;
import laboratorio.Empleados.Repositorios.AuxiliarRepository;
import laboratorio.Empleados.Repositorios.BionalistaRepository;
import laboratorio.Empleados.Repositorios.SecretariaRepository;
import laboratorio.Paciente.Entidades.Paciente;
import laboratorio.Paciente.Excepciones.DuplicateDocumentException;
import laboratorio.Paciente.Repositorios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteRestController {

    @Autowired
    private BionalistaRepository bioanalistaRepository;

    @Autowired
    private AuxiliarRepository auxiliarRepository;

    @Autowired
    private SecretariaRepository secretariaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ARSRepository arsRepository;

    @ExceptionHandler(DuplicateDocumentException.class)
    public ResponseEntity<String> handleDuplicateDocumentException(DuplicateDocumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("bioanalistas", bioanalistaRepository.count());
        counts.put("auxiliares", auxiliarRepository.count());
        counts.put("secretarias", secretariaRepository.count());
        counts.put("pacientes", pacienteRepository.count());
        return ResponseEntity.ok(counts);
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<Paciente>> buscarPacientes(@RequestBody Map<String, String> criterios) {
        List<Paciente> pacientes = pacienteRepository.buscarPorCriterios(
                criterios.get("nombre"),
                criterios.get("apellido"),
                criterios.get("tipoDocumento"),
                criterios.get("documento"),
                criterios.get("fechaNacimiento"),
                criterios.get("telefono"),
                criterios.get("direccion"),
                criterios.get("seguroSalud")
        );
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping("/add")
    public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
        // Verificar si el documento ya está registrado
        if (pacienteRepository.findByDocumento(paciente.getDocumento()).isPresent()) {
            throw new DuplicateDocumentException("Este documento ya está registrado");
        }

        // Usar la cédula (documento) como usuario
        String usuario = paciente.getDocumento();
        String password = generatePassword(8);

        // Verificar si ARS es "NO ASEGURADO" o está vacío
        ARS ars = null;
        if (paciente.getSeguroSalud() != null && !paciente.getSeguroSalud().equals("NO ASEGURADO")) {
            ars = arsRepository.findByNombreARS(paciente.getSeguroSalud());
            if (ars == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ARS no encontrado para el seguro proporcionado");
            }
        }

        paciente.setArs(ars);

        Paciente savedPaciente = pacienteRepository.save(new Paciente(
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getTipoDocumento(),
                paciente.getDocumento(),
                paciente.getFechaNacimiento(),
                paciente.getTelefono(),
                paciente.getDireccion(),
                paciente.getSeguroSalud(),
                paciente.isActivo(),
                paciente.getArs(),
                paciente.getDoctores(),
                paciente.getNss(),
                usuario,  // Usuario basado en la cédula
                password,  // Contraseña generada
                paciente.getCorreo()
        ));

        return ResponseEntity.ok(savedPaciente);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente existingPaciente = pacienteRepository.findById(id).orElse(null);
        if (existingPaciente == null) {
            return ResponseEntity.notFound().build();
        }

        ARS ars = arsRepository.findByNombreARS(paciente.getSeguroSalud());
        if (ars == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ARS no encontrado para el seguro proporcionado");
        }

        existingPaciente.setNombre(paciente.getNombre());
        existingPaciente.setApellido(paciente.getApellido());
        existingPaciente.setTipoDocumento(paciente.getTipoDocumento());
        existingPaciente.setDocumento(paciente.getDocumento());
        existingPaciente.setFechaNacimiento(paciente.getFechaNacimiento());
        existingPaciente.setTelefono(paciente.getTelefono());
        existingPaciente.setDireccion(paciente.getDireccion());
        existingPaciente.setSeguroSalud(paciente.getSeguroSalud());
        existingPaciente.setNss(paciente.getNss()); // NSS agregado aquí
        existingPaciente.setActivo(paciente.isActivo());
        existingPaciente.setArs(ars);
        existingPaciente.setDoctores(paciente.getDoctores());
        existingPaciente.setCorreo(paciente.getCorreo());

        Paciente updatedPaciente = pacienteRepository.save(existingPaciente);
        return ResponseEntity.ok(updatedPaciente);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Paciente> deletePaciente(@PathVariable Long id) {
        Paciente existingPaciente = pacienteRepository.findById(id).orElse(null);
        if (existingPaciente == null) {
            return ResponseEntity.notFound().build();
        }
        existingPaciente.setActivo(false);
        Paciente updatedPaciente = pacienteRepository.save(existingPaciente);
        pacienteRepository.delete(existingPaciente);
        return ResponseEntity.ok(updatedPaciente);
    }

    private String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        return random.ints(length, 0, chars.length())
                .mapToObj(i -> String.valueOf(chars.charAt(i)))
                .collect(Collectors.joining());
    }
}
