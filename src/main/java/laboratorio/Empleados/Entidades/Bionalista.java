package laboratorio.Empleados.Entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("Bionalista")
public class Bionalista extends Empleado {
    // Constructor
    public Bionalista() {}

    public Bionalista(String nombre, String apellido, String usuario, String password, String correo, String tiempoTrabajando, boolean activo, String sexo) {
        super(nombre, apellido, usuario, password, correo, tiempoTrabajando, activo, sexo);
    }
}