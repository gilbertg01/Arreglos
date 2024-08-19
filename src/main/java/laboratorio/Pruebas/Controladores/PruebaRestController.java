package laboratorio.Pruebas.Controladores;

import laboratorio.Pruebas.Entidades.Prueba;
import laboratorio.Pruebas.Repositorios.PruebaRepository;
import laboratorio.Pruebas.Servicio.PruebaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaRestController {

    @Autowired
    private PruebaRepository pruebaRepositorio;

    @Autowired
    private PruebaService pruebaService;

    @GetMapping
    public List<Prueba> getAllPruebas() {
        return pruebaRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prueba> getPruebaById(@PathVariable UUID id) {
        Optional<Prueba> prueba = pruebaRepositorio.findById(id);
        return prueba.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Prueba createPrueba(@RequestBody Prueba prueba) {
        // Generar el próximo código de prueba disponible basado en la categoría
        String codigoBase = pruebaService.getBaseCodigo(prueba.getCategoriaPrueba());
        String codigoPrueba = pruebaService.generateNextCodigo(codigoBase);
        prueba.setCodigoPrueba(codigoPrueba);

        return pruebaRepositorio.save(prueba);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prueba> updatePrueba(@PathVariable UUID id, @RequestBody Prueba pruebaDetails) {
        Optional<Prueba> optionalPrueba = pruebaRepositorio.findById(id);
        if (optionalPrueba.isPresent()) {
            Prueba prueba = optionalPrueba.get();
            prueba.setNombrePrueba(pruebaDetails.getNombrePrueba());
            prueba.setPreparacionPaciente(pruebaDetails.getPreparacionPaciente());
            prueba.setMuestraRequerida(pruebaDetails.getMuestraRequerida());
            prueba.setCodigoPrueba(pruebaDetails.getCodigoPrueba());
            prueba.setCosto(pruebaDetails.getCosto());
            prueba.setCategoriaPrueba(pruebaDetails.getCategoriaPrueba());
            prueba.setNotasAdicionales(pruebaDetails.getNotasAdicionales());
            prueba.setEstado(pruebaDetails.isEstado());
            Prueba updatedPrueba = pruebaRepositorio.save(prueba);
            return ResponseEntity.ok(updatedPrueba);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> deletePrueba(@PathVariable UUID id) {
        Optional<Prueba> optionalPrueba = pruebaRepositorio.findById(id);
        if (optionalPrueba.isPresent()) {
            Prueba prueba = optionalPrueba.get();
            prueba.setEstado(false);
            pruebaRepositorio.save(prueba);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/codigo")
    public ResponseEntity<String> getCodigoPrueba(@RequestParam String base) {
        String codigo = pruebaService.generateNextCodigo(base);
        return ResponseEntity.ok(codigo);
    }
}
