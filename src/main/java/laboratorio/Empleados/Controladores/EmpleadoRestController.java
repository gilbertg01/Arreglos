package laboratorio.Empleados.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import laboratorio.Empleados.Entidades.*;
import laboratorio.Empleados.Repositorios.EmpleadoRepository;
import laboratorio.Email.Servicio.EmailService;


import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoRestController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado empleado) {
        String plaintextPassword = empleado.getPassword();
        String encodedPassword = passwordEncoder.encode(plaintextPassword);
        empleado.setPassword(encodedPassword);

        Empleado savedEmpleado;
        switch (empleado.getTipoEmpleado()) {
            case "Auxiliar":
                savedEmpleado = empleadoRepository.save(new Auxiliar(
                        empleado.getNombre(),
                        empleado.getApellido(),
                        empleado.getUsuario(),
                        empleado.getPassword(),
                        empleado.getCorreo(),
                        empleado.getTiempoTrabajando(),
                        empleado.isActivo(),
                        empleado.getSexo()));
                break;
            case "Bionalista":
                savedEmpleado = empleadoRepository.save(new Bionalista(
                        empleado.getNombre(),
                        empleado.getApellido(),
                        empleado.getUsuario(),
                        empleado.getPassword(),
                        empleado.getCorreo(),
                        empleado.getTiempoTrabajando(),
                        empleado.isActivo(),
                        empleado.getSexo()));
                break;
            case "Secretaria":
                savedEmpleado = empleadoRepository.save(new Secretaria(
                        empleado.getNombre(),
                        empleado.getApellido(),
                        empleado.getUsuario(),
                        empleado.getPassword(),
                        empleado.getCorreo(),
                        empleado.getTiempoTrabajando(),
                        empleado.isActivo(),
                        empleado.getSexo()));
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        emailService.enviarCorreoBienvenidaEmpleado(savedEmpleado, plaintextPassword);
        return ResponseEntity.ok(savedEmpleado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }

        if (empleado instanceof Auxiliar) {
            empleado.setTipoEmpleado("Auxiliar");
        } else if (empleado instanceof Bionalista) {
            empleado.setTipoEmpleado("Bionalista");
        } else if (empleado instanceof Secretaria) {
            empleado.setTipoEmpleado("Secretaria");
        }

        return ResponseEntity.ok(empleado);
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        for (Empleado empleado : empleados) {
            if (empleado instanceof Auxiliar) {
                empleado.setTipoEmpleado("Auxiliar");
            } else if (empleado instanceof Bionalista) {
                empleado.setTipoEmpleado("Bionalista");
            } else if (empleado instanceof Secretaria) {
                empleado.setTipoEmpleado("Secretaria");
            }
        }
        return ResponseEntity.ok(empleados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado existingEmpleado = empleadoRepository.findById(id).orElse(null);
        if (existingEmpleado == null) {
            return ResponseEntity.notFound().build();
        }

        existingEmpleado.setNombre(empleado.getNombre());
        existingEmpleado.setApellido(empleado.getApellido());
        existingEmpleado.setUsuario(empleado.getUsuario());

        if (!passwordEncoder.matches(empleado.getPassword(), existingEmpleado.getPassword())) {
            String encodedPassword = passwordEncoder.encode(empleado.getPassword());
            existingEmpleado.setPassword(encodedPassword);
        }

        existingEmpleado.setCorreo(empleado.getCorreo());
        existingEmpleado.setTiempoTrabajando(empleado.getTiempoTrabajando());
        existingEmpleado.setActivo(empleado.isActivo());
        existingEmpleado.setSexo(empleado.getSexo());

        Empleado updatedEmpleado = empleadoRepository.save(existingEmpleado);
        return ResponseEntity.ok(updatedEmpleado);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Empleado> deleteEmpleado(@PathVariable Long id) {
        Empleado existingEmpleado = empleadoRepository.findById(id).orElse(null);
        if (existingEmpleado == null) {
            return ResponseEntity.notFound().build();
        }

        existingEmpleado.setActivo(false);
        Empleado updatedEmpleado = empleadoRepository.save(existingEmpleado);
        return ResponseEntity.ok(updatedEmpleado);
    }

}
