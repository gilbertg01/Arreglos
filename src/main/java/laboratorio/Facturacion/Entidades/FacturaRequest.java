package laboratorio.Facturacion.Entidades;

import java.util.List;
import java.util.UUID;

// FacturaRequest.java
public class FacturaRequest {
    private Long pacienteId;
    private List<String> pruebas;
    private Long metodoPagoId;
    private Long doctorId;

    // Getters y Setters

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public List<String> getPruebas() {
        return pruebas;
    }

    public void setPruebas(List<String> pruebas) {
        this.pruebas = pruebas;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
