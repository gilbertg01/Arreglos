package laboratorio.Facturacion.Entidades;

import laboratorio.Paciente.Entidades.PacienteDTO;

import java.util.Date;

public class FacturaDTO {

    private Long id;
    private String numeroFactura;
    private PacienteDTO paciente;
    private Date fechaEmision;
    private double total;

    // Constructor para convertir Factura a FacturaDTO
    public FacturaDTO(Factura factura) {
        this.id = factura.getId();
        this.numeroFactura = factura.getNumeroFactura();
        this.paciente = new PacienteDTO(factura.getPaciente());
        this.fechaEmision = factura.getFechaEmision();
        this.total = factura.getTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
