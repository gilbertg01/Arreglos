package laboratorio.Doctores.Entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import laboratorio.Paciente.Entidades.Paciente;
import java.util.List;

@Entity
@Table(name = "doctores")
public class Doctores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

@Column(name = "apellido")
    private String apellido;

@Column(name = "especialidad")
    private String especialidad;

@Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "doctores")
    @JsonIgnore
    private List<Paciente> pacientes;

@Column(name = "activo")
    private boolean activo;

public Doctores() {}

public Doctores(String nombre, String apellido, String especialidad, String telefono, boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.activo = activo;
    }

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

public String getEspecialidad() {
        return especialidad;
    }

public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

public String getTelefono() {
        return telefono;
    }

public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

public List<Paciente> getPacientes() {
        return pacientes;
    }

public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

public boolean isActivo() {
        return activo;
    }

public void setActivo(boolean activo) {
        this.activo = activo;
    }


}
