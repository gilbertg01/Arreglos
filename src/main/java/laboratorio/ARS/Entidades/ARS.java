package laboratorio.ARS.Entidades;

import jakarta.persistence.*;
import laboratorio.Pruebas.Entidades.Prueba;
import java.util.List;

@Entity
@Table(name = "ars")
public class ARS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_ars", nullable = false, length = 100)
    private String nombreARS;

    @Column(name = "RNC", nullable = false, length = 15)
    private long RNC;

    @Column(name = "representante", length = 100)
    private String representante;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "telefono", length = 15)
    private long telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "descuento", nullable = false)
    private double descuento;

    public ARS() {}

    public ARS(String nombreARS, long RNC, String representante, String direccion, long telefono, String correo,  double descuento) {
        this.nombreARS = nombreARS;
        this.RNC = RNC;
        this.representante = representante;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.activo = true; // default value
        this.descuento = descuento;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreARS() {
        return nombreARS;
    }

    public void setNombreARS(String nombreARS) {
        this.nombreARS = nombreARS;
    }

    public long getRNC() {
        return RNC;
    }

    public void setRNC(long RNC) {
        this.RNC = RNC;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
