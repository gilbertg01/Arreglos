package laboratorio.Facturacion.Repositorios;

import laboratorio.Facturacion.Entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestController
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByCompletadaFalse();
}
