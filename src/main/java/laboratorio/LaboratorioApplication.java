package laboratorio;

import laboratorio.Empleados.Entidades.Admin;
import laboratorio.Empleados.Entidades.Bionalista;
import laboratorio.Empleados.Entidades.Empleado;
import laboratorio.Empleados.Entidades.Secretaria;
import laboratorio.Empleados.Repositorios.EmpleadoRepository;
import laboratorio.Facturacion.Entidades.MetodoPago;
import laboratorio.Facturacion.Repositorios.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@EnableAsync
public class LaboratorioApplication implements CommandLineRunner {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(LaboratorioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        verificarYCrearAdmin();
        if (metodoPagoRepository.count() == 0) {
            MetodoPago efectivo = new MetodoPago("Efectivo", "Pago en efectivo");
            MetodoPago tarjeta = new MetodoPago("Tarjeta", "Pago con tarjeta de crédito/débito");
            metodoPagoRepository.save(efectivo);
            metodoPagoRepository.save(tarjeta);
        }
    }

    private void verificarYCrearAdmin() {
        Optional<Empleado> adminUser = empleadoRepository.findByUsuario("admin");
        if (adminUser.isEmpty()) {
            String encodedPassword = passwordEncoder.encode("admin");
            String encodedPassword2 = passwordEncoder.encode("123");
            Admin admin = new Admin("Admin", "Admin", "admin", encodedPassword, "admin@example.com", "0", true, "M");
            Secretaria sec = new Secretaria("Sec", "Sec", "sec", encodedPassword2, "sec@example.com", "0", true, "F");
            Bionalista bio = new Bionalista("Bio", "Bio", "bio", encodedPassword2, "bio@example.com", "0", true, "M");
            empleadoRepository.save(admin);
            empleadoRepository.save(sec);
            empleadoRepository.save(bio);
            System.out.println("Usuario administrador creado: " + admin.getUsuario());
        } else {
            System.out.println("Usuario administrador ya existe: " + adminUser.get().getUsuario());
        }
    }
}