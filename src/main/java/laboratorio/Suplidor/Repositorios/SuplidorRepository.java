package laboratorio.Suplidor.Repositorios;

import laboratorio.Suplidor.Entidades.Suplidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

@RepositoryRestController
public interface SuplidorRepository extends JpaRepository<Suplidor, Long> {
}
