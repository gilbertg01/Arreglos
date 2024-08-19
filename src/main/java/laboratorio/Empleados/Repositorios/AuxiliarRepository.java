package laboratorio.Empleados.Repositorios;

import laboratorio.Empleados.Entidades.Auxiliar;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestController
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {
}