package laboratorio.Facturacion.Repositorios;

import laboratorio.Facturacion.Entidades.Factura;
import laboratorio.Facturacion.Entidades.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {
}
