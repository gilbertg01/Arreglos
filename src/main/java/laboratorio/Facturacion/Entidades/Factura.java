package laboratorio.Facturacion.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import laboratorio.Paciente.Entidades.Paciente;
import laboratorio.Facturacion.Entidades.MetodoPago;
import jakarta.persistence.*;
import laboratorio.Pruebas.Entidades.Prueba;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_factura", nullable = false, unique = true)
    private String numeroFactura;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision", nullable = false)
    private Date fechaEmision;

    @Column(name = "total", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private MetodoPago metodoPago;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<FacturaPrueba> facturaPruebas = new ArrayList<>();

    @Column(name = "completada", nullable = false)
    private boolean completada = false;

    public Factura() {
    }

    public Factura(String numeroFactura, Paciente paciente, Date fechaEmision, double total, MetodoPago metodoPago) {
        this.numeroFactura = numeroFactura;
        this.paciente = paciente;
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.metodoPago = metodoPago;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<FacturaPrueba> getFacturaPruebas() {
        return facturaPruebas;
    }

    public void setFacturaPruebas(List<FacturaPrueba> facturaPruebas) {
        this.facturaPruebas = facturaPruebas;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    // Método toString para imprimir detalles de la factura
    @Override
    public String toString() {
        return "Factura{" +
                "numeroFactura='" + numeroFactura + '\'' +
                ", paciente=" + paciente.getNombre() +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                ", metodoPago=" + metodoPago.getNombreMetodo() +
                '}';
    }
}
