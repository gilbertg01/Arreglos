package laboratorio.Suplidor.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import laboratorio.Suplidor.Entidades.*;
import laboratorio.Suplidor.Repositorios.SuplidorRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suplidores")
public class SuplidorRestController {

    @Autowired
    private SuplidorRepository suplidorRepository;

    @PostMapping("/add")
    public ResponseEntity<Suplidor> addSuplidor(@RequestBody Suplidor suplidor) {
        Suplidor savedSuplidor = suplidorRepository.save(suplidor);
        return ResponseEntity.ok(savedSuplidor);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Suplidor> getSuplidorById(@PathVariable Long id) {
        Optional<Suplidor> suplidor = suplidorRepository.findById(id);
        if (suplidor.isPresent()) {
            return ResponseEntity.ok(suplidor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Suplidor> getAllSuplidores() {
        return suplidorRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suplidor> updateSuplidor(@PathVariable Long id, @RequestBody Suplidor suplidorDetails) {
        Optional<Suplidor> suplidor = suplidorRepository.findById(id);
        if (suplidor.isPresent()) {
            Suplidor updatedSuplidor = suplidor.get();
            updatedSuplidor.setNombre(suplidorDetails.getNombre());
            updatedSuplidor.setDireccion(suplidorDetails.getDireccion());
            updatedSuplidor.setTelefono(suplidorDetails.getTelefono());
            updatedSuplidor.setDescripcion(suplidorDetails.getDescripcion());
            updatedSuplidor.setActivo(suplidorDetails.isActivo());
            return ResponseEntity.ok(suplidorRepository.save(updatedSuplidor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Suplidor> deleteSuplidor(@PathVariable Long id){
        Suplidor existingSuplidor = suplidorRepository.findById(id).orElse(null);
        if(existingSuplidor==null){
            return ResponseEntity.notFound().build();
        }
        existingSuplidor.setActivo(false);
        Suplidor updatedSuplidor = suplidorRepository.save(existingSuplidor);
        return ResponseEntity.ok(updatedSuplidor);
    }


}
