package laboratorio.Doctores.Repositorios;

import laboratorio.Doctores.Entidades.Doctores;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@RepositoryRestController
public interface DoctoresRepository extends JpaRepository<Doctores, Long>{
}
