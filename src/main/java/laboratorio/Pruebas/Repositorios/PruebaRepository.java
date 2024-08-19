package laboratorio.Pruebas.Repositorios;

import laboratorio.Pruebas.Entidades.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, UUID> {
    List<Prueba> findByCodigoPruebaStartingWith(String base);
    Optional<Prueba> findByNombrePrueba(String nombrePrueba);
}
