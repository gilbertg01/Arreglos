package laboratorio.Empleados.Servicios;

import laboratorio.Empleados.Entidades.Empleado;
import laboratorio.Empleados.Repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Empleado registerEmpleado(String nombre, String apellido, String usuario, String rawPassword, String correo, String tiempoTrabajando, boolean activo, String sexo) {
        Empleado empleado = new Empleado(nombre, apellido, usuario, passwordEncoder.encode(rawPassword), correo, tiempoTrabajando, activo, sexo);
        return empleadoRepository.save(empleado);
    }
}
