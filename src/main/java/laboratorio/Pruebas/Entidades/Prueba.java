package laboratorio.Pruebas.Entidades;

import jakarta.persistence.*;
import laboratorio.Paciente.Entidades.Paciente;
import laboratorio.ARS.Entidades.ARS;
import laboratorio.Resultado.Entidades.Resultado;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "prueba")
public class Prueba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPrueba;

    @Column(name = "nombre_prueba", nullable = false, length = 100)
    private String nombrePrueba;

    @Column(name = "preparacion_paciente", length = 255)
    private String preparacionPaciente;

    @Column(name = "muestra_requerida", length = 50)
    private String muestraRequerida;

    @Column(name = "codigo_prueba", length = 50, unique = true)
    private String codigoPrueba;

    @Column(name = "costo", nullable = false)
    private double costo;

    @Column(name = "categoria_prueba", length = 100)
    private String categoriaPrueba;

    @Column(name = "notas_adicionales", length = 255)
    private String notasAdicionales;

    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    @Column(name = "estado")
    private boolean estado;

    // Constructor
    public Prueba() {
        // Para JPA
    }

    public Prueba(String nombrePrueba, String preparacionPaciente, String muestraRequerida, double costo, String categoriaPrueba, String notasAdicionales, boolean estado) {
        this.idPrueba = UUID.randomUUID();
        this.nombrePrueba = nombrePrueba;
        this.preparacionPaciente = preparacionPaciente;
        this.muestraRequerida = muestraRequerida;
        this.codigoPrueba = generarCodigoPrueba(muestraRequerida);
        this.costo = costo;
        this.categoriaPrueba = categoriaPrueba;
        this.notasAdicionales = notasAdicionales;
        this.fechaCreacion = new Date();
        this.fechaActualizacion = new Date();
        this.estado = estado;
    }

    // Método para generar el código de la prueba
    private String generarCodigoPrueba(String muestraRequerida) {
        String codigoBase;
        switch (muestraRequerida.toLowerCase()) {
            case "sangre":
                codigoBase = "SAG";
                break;
            case "saliva":
                codigoBase = "SAL";
                break;
            case "orina":
                codigoBase = "ORI";
                break;
            case "materia fecal":
                codigoBase = "MATFE";
                break;
            default:
                codigoBase = "UNK"; // Unknown sample type
                break;
        }
        return codigoBase + "01"; // Placeholder, actual counter logic to be implemented
    }

    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
        fechaActualizacion = new Date();
    }

    // Getters y Setters

    public UUID getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(UUID idPrueba) {
        this.idPrueba = idPrueba;
    }

    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
        actualizarFechaActualizacion();
    }

    public String getPreparacionPaciente() {
        return preparacionPaciente;
    }

    public void setPreparacionPaciente(String preparacionPaciente) {
        this.preparacionPaciente = preparacionPaciente;
        actualizarFechaActualizacion();
    }

    public String getMuestraRequerida() {
        return muestraRequerida;
    }

    public void setMuestraRequerida(String muestraRequerida) {
        this.muestraRequerida = muestraRequerida;
        this.codigoPrueba = generarCodigoPrueba(muestraRequerida);
        actualizarFechaActualizacion();
    }

    public String getCodigoPrueba() {
        return codigoPrueba;
    }

    public void setCodigoPrueba(String codigoPrueba) {
        this.codigoPrueba = codigoPrueba;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
        actualizarFechaActualizacion();
    }

    public String getCategoriaPrueba() {
        return categoriaPrueba;
    }

    public void setCategoriaPrueba(String categoriaPrueba) {
        this.categoriaPrueba = categoriaPrueba;
        actualizarFechaActualizacion();
    }

    public String getNotasAdicionales() {
        return notasAdicionales;
    }

    public void setNotasAdicionales(String notasAdicionales) {
        this.notasAdicionales = notasAdicionales;
        actualizarFechaActualizacion();
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
        actualizarFechaActualizacion();
    }

    // Método para actualizar la fecha de actualización
    private void actualizarFechaActualizacion() {
        this.fechaActualizacion = new Date();
    }
}