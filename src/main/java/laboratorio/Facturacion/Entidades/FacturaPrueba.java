package laboratorio.Facturacion.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import laboratorio.Pruebas.Entidades.Prueba;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Table(name = "factura_prueba")
public class FacturaPrueba {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "prueba_id", nullable = false)
    private Prueba prueba;

    @Column(name = "resultado", length = 255)
    private String resultado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_asignacion", nullable = false)
    private Date fechaAsignacion = new Date();

    public FacturaPrueba() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
}
