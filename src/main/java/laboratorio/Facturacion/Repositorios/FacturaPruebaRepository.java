package laboratorio.Facturacion.Repositorios;

import laboratorio.Facturacion.Entidades.Factura;
import laboratorio.Facturacion.Entidades.FacturaPrueba;
import laboratorio.Pruebas.Entidades.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;
import java.util.Optional;

public interface FacturaPruebaRepository extends JpaRepository<FacturaPrueba, Long> {
    Optional<FacturaPrueba> findByFacturaAndPrueba(Factura factura, Prueba prueba);
}
