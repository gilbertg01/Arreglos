package laboratorio.Paciente.Entidades;

public class PacienteDTO {
    private String nombre;
    private String apellido;

    public PacienteDTO(Paciente paciente) {
        this.nombre = paciente.getNombre();
        this.apellido = paciente.getApellido();
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
}
