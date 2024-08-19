package laboratorio.Empleados.Servicios;

import laboratorio.Empleados.Entidades.Empleado;
import laboratorio.Empleados.Repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empleado> empleado = empleadoRepository.findByUsuario(username);
        if (empleado.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(empleado.get().getUsuario(), empleado.get().getPassword(), getAuthorities(empleado.orElse(null)));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Empleado empleado) {
        String role = "ROLE_" + empleado.getTipoEmpleado().toUpperCase();
        System.out.println("Assigned Role: " + role); // Log para depuración
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
