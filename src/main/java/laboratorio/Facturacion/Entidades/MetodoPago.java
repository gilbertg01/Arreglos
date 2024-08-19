package laboratorio.Facturacion.Entidades;

import laboratorio.Paciente.Entidades.Paciente;
import jakarta.persistence.*;

@Entity
@Table(name = "metodo_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_metodo", nullable = false, length = 100)
    private String nombreMetodo;

    @Column(name = "detalle", length = 255)
    private String detalle;

    public MetodoPago() {
    }

    public MetodoPago(String nombreMetodo, String detalle) {
        this.nombreMetodo = nombreMetodo;
        this.detalle = detalle;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
