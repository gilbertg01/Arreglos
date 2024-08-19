package laboratorio.ARS.Controladores;

import laboratorio.ARS.Entidades.ARS;
import laboratorio.ARS.Repositorio.ARSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ars")
public class ARSController {

    @Autowired
    private ARSRepository arsRepository;

    @PostMapping("/add")
    public ResponseEntity<ARS> addARS(@RequestBody ARS ars) {
        ARS savedARS = arsRepository.save(new ARS(
                ars.getNombreARS(),
                ars.getRNC(),
                ars.getRepresentante(),
                ars.getDireccion(),
                ars.getTelefono(),
                ars.getCorreo(),
                ars.getDescuento()));

        return ResponseEntity.ok(savedARS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ARS> getARSById(@PathVariable Long id) {
        ARS ars = arsRepository.findById(id).orElse(null);
        if (ars == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ars);
    }

    @GetMapping
    public ResponseEntity<List<ARS>> getAllARS() {
        List<ARS> arsList = arsRepository.findAll();
        return ResponseEntity.ok(arsList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ARS> updateARS(@PathVariable Long id, @RequestBody ARS ars) {
        ARS existingARS = arsRepository.findById(id).orElse(null);
        if (existingARS == null) {
            return ResponseEntity.notFound().build();
        }

        existingARS.setNombreARS(ars.getNombreARS());
        existingARS.setRNC(ars.getRNC());
        existingARS.setRepresentante(ars.getRepresentante());
        existingARS.setDireccion(ars.getDireccion());
        existingARS.setTelefono(ars.getTelefono());
        existingARS.setCorreo(ars.getCorreo());
        existingARS.setActivo(ars.isActivo());
        existingARS.setDescuento(ars.getDescuento());

        ARS updatedARS = arsRepository.save(existingARS);
        return ResponseEntity.ok(updatedARS);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ARS> deleteARS(@PathVariable Long id) {
        ARS existingARS = arsRepository.findById(id).orElse(null);
        if (existingARS == null) {
            return ResponseEntity.notFound().build();
        }
        existingARS.setActivo(false);
        ARS updatedARS = arsRepository.save(existingARS);
        arsRepository.delete(existingARS);
        return ResponseEntity.ok(updatedARS);
    }
}


