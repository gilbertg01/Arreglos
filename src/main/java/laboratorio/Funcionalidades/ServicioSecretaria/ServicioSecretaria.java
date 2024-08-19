package laboratorio.Funcionalidades.ServicioSecretaria;

import laboratorio.Paciente.Entidades.Paciente;
import laboratorio.Paciente.Repositorios.PacienteRepository;

public class ServicioSecretaria {
    private PacienteRepository pacienteRepository;

    public Paciente addPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

}
