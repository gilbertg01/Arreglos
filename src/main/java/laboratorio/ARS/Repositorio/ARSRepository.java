package laboratorio.ARS.Repositorio;
import laboratorio.ARS.Entidades.ARS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

@RepositoryRestController
public interface ARSRepository extends JpaRepository<ARS, Long>{
    ARS findByNombreARS(String nombreARS);
}
