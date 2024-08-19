package laboratorio.Resultado.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import laboratorio.Facturacion.Entidades.Factura;
import laboratorio.Paciente.Entidades.Paciente;
import laboratorio.Pruebas.Entidades.Prueba;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resultado")
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "prueba_id", nullable = false)
    @JsonIgnore
    private Prueba prueba;


    @Column(name = "resultado_texto", length = 255)
    private String resultadoTexto;

    @ManyToOne
    @JsonIgnore
    private Paciente paciente;

    @Column(name = "activo")
    private boolean activo;

    public Resultado() {
    }

    public Resultado(Factura factura, Prueba prueba, String resultadoTexto, Paciente paciente) {
        this.factura = factura;
        this.prueba = prueba;
        this.resultadoTexto = resultadoTexto;
        this.paciente = paciente;
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResultadoTexto() {
        return resultadoTexto;
    }

    public void setResultadoTexto(String resultadoTexto) {
        this.resultadoTexto = resultadoTexto;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Factura getFactura() {
        return factura;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "id=" + id +
                ", factura=" + factura.getNumeroFactura() +
                ", prueba=" + prueba.getNombrePrueba() +
                ", resultadoTexto='" + resultadoTexto + '\'' +
                ", paciente=" + paciente.getNombre() +
                ", activo=" + activo +
                '}';
    }
}
