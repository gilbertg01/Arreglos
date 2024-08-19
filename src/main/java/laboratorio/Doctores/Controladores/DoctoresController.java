package laboratorio.Doctores.Controladores;

import laboratorio.Doctores.Entidades.Doctores;
import laboratorio.Doctores.Repositorios.DoctoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctoresController {

    @Autowired
    private DoctoresRepository doctoresRepository;

    @PostMapping("/add")
    public ResponseEntity<Doctores> addDoctor(@RequestBody Doctores doctor) {
        Doctores savedDoctor = doctoresRepository.save(new Doctores(
                doctor.getNombre(),
                doctor.getApellido(),
                doctor.getEspecialidad(),
                doctor.getTelefono(),
                doctor.isActivo()));

        return ResponseEntity.ok(savedDoctor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctores> getDoctorById(@PathVariable Long id) {
        Doctores doctor = doctoresRepository.findById(id).orElse(null);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    @GetMapping
    public ResponseEntity<List<Doctores>> getAllDoctores() {
        List<Doctores> doctores = doctoresRepository.findAll();
        return ResponseEntity.ok(doctores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctores> updateDoctor(@PathVariable Long id, @RequestBody Doctores doctor) {
        Doctores existingDoctor = doctoresRepository.findById(id).orElse(null);
        if (existingDoctor == null) {
            return ResponseEntity.notFound().build();
        }

        existingDoctor.setNombre(doctor.getNombre());
        existingDoctor.setApellido(doctor.getApellido());
        existingDoctor.setEspecialidad(doctor.getEspecialidad());
        existingDoctor.setTelefono(doctor.getTelefono());
        existingDoctor.setActivo(doctor.isActivo());

        Doctores updatedDoctor = doctoresRepository.save(existingDoctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Doctores> deleteDoctor(@PathVariable Long id) {
        Doctores existingDoctor = doctoresRepository.findById(id).orElse(null);
        if (existingDoctor == null) {
            return ResponseEntity.notFound().build();
        }
        existingDoctor.setActivo(false);
        Doctores updatedDoctor = doctoresRepository.save(existingDoctor);
        doctoresRepository.delete(existingDoctor);
        return ResponseEntity.ok(updatedDoctor);
    }
}
