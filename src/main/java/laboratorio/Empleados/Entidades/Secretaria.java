package laboratorio.Empleados.Entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Secretaria")
public class Secretaria extends Empleado {
    // Constructor
    public Secretaria() {}

    public Secretaria(String nombre, String apellido, String usuario, String password, String correo, String tiempoTrabajando, boolean activo, String sexo) {
        super(nombre, apellido, usuario, password, correo, tiempoTrabajando, activo, sexo);
    }
}
