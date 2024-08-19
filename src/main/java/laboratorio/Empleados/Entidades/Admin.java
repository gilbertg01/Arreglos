package laboratorio.Empleados.Entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Empleado {

    public Admin() {
    }

    public Admin(String nombre, String apellido, String usuario, String password, String correo, String tiempoTrabajando, boolean activo, String sexo) {
        super(nombre, apellido, usuario, password, correo, tiempoTrabajando, activo, sexo);
    }
}
