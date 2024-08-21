package laboratorio.Paciente.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import laboratorio.ARS.Entidades.ARS;
import laboratorio.Doctores.Entidades.Doctores;
import laboratorio.Facturacion.Entidades.Factura;
import laboratorio.Pruebas.Entidades.Prueba;
import laboratorio.Resultado.Entidades.Resultado;

import java.util.List;

@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "documento")
    private String documento;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "seguro_salud")
    private String seguroSalud;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "nss", nullable = true, length = 20)
    private String nss;

    @ManyToOne
    @JoinColumn(name = "ars_id")
    private ARS ars;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctores doctores;

    @Column(name = "usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Factura> facturas;

    public Paciente() {}

    public Paciente(String nombre, String apellido, String tipoDocumento, String documento, String fechaNacimiento, String telefono, String direccion, String seguroSalud, boolean activo, ARS ars, Doctores doctores, String nss, String usuario, String password, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.seguroSalud = seguroSalud;
        this.activo = activo;
        this.ars = ars;
        this.doctores = doctores;
        this.nss = nss;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSeguroSalud() {
        return seguroSalud;
    }

    public void setSeguroSalud(String seguroSalud) {
        this.seguroSalud = seguroSalud;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ARS getArs() {
        return ars;
    }

    public void setArs(ARS ars) {
        this.ars = ars;
    }

    public Doctores getDoctores() {
        return doctores;
    }

    public void setDoctores(Doctores doctores) {
        this.doctores = doctores;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}