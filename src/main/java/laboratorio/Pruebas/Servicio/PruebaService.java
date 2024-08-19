package laboratorio.Pruebas.Servicio;

import laboratorio.Pruebas.Entidades.Prueba;
import laboratorio.Pruebas.Repositorios.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;

    public String generateNextCodigo(String base) {
        List<Prueba> pruebas = pruebaRepository.findByCodigoPruebaStartingWith(base);
        int maxNumber = 0;

        for (Prueba prueba : pruebas) {
            String codigo = prueba.getCodigoPrueba().replace(base, "");
            try {
                int number = Integer.parseInt(codigo);
                if (number > maxNumber) {
                    maxNumber = number;
                }
            } catch (NumberFormatException e) {
                // Ignorar errores de parseo
            }
        }

        return base + String.format("%02d", maxNumber + 1);
    }

    public String getBaseCodigo(String categoriaPrueba) {
        if ("sangre".equalsIgnoreCase(categoriaPrueba)) {
            return "SAG";
        } else if ("saliva".equalsIgnoreCase(categoriaPrueba)) {
            return "SAL";
        } else if ("orina".equalsIgnoreCase(categoriaPrueba)) {
            return "ORI";
        } else if ("materia fecal".equalsIgnoreCase(categoriaPrueba)) {
            return "MATFE";
        } else {
            return "UNK";
        }
    }
}
