package laboratorio.Empleados.Entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Auxiliar")
public class Auxiliar extends Empleado {
    // Constructor
    public Auxiliar() {}

    public Auxiliar(String nombre, String apellido, String usuario, String password, String correo, String tiempoTrabajando, boolean activo, String sexo) {
        super(nombre, apellido, usuario, password, correo, tiempoTrabajando, activo, sexo);
    }
}
