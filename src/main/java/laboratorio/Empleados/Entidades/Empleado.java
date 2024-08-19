package laboratorio.Empleados.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "tiempo_trabajando", nullable = false, length = 10)
    private String tiempoTrabajando;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "sexo", nullable = false, length = 10)
    private String sexo;

    @Transient
    private String tipoEmpleado;

    // Constructor
    public Empleado() {}

    public Empleado(String nombre, String apellido, String usuario, String password, String correo, String tiempoTrabajando, boolean activo, String sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.correo = correo;
        this.tiempoTrabajando = tiempoTrabajando;
        this.activo = activo;
        this.sexo = sexo;
    }

    // Getters y setters
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

    public String getTiempoTrabajando() {
        return tiempoTrabajando;
    }

    public void setTiempoTrabajando(String tiempoTrabajando) {
        this.tiempoTrabajando = tiempoTrabajando;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoEmpleado() {
        if (this.getClass().isAnnotationPresent(DiscriminatorValue.class)) {
            return this.getClass().getAnnotation(DiscriminatorValue.class).value();
        }
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
