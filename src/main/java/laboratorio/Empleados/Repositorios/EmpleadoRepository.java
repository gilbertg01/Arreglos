package laboratorio.Empleados.Repositorios;

import laboratorio.Empleados.Entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByUsuario(String usuario);

}



