package laboratorio.Empleados.Repositorios;

import laboratorio.Empleados.Entidades.Secretaria;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestController
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
    Secretaria findByUsuario(String usuario);
}