package laboratorio.Empleados.Repositorios;

import laboratorio.Empleados.Entidades.Bionalista;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestController
public interface BionalistaRepository extends JpaRepository<Bionalista, Long> {
    Bionalista findByUsuario(String usuario);
}