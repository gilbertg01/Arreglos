package laboratorio.Resultado.Entidades;

import java.util.UUID;

public class ResultadoRequest {

    private  Long facturaId;
    private UUID pruebaId;
    private String resultadoTexto;

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public UUID getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(UUID pruebaId) {
        this.pruebaId = pruebaId;
    }

    public String getResultadoTexto() {
        return resultadoTexto;
    }

    public void setResultadoTexto(String resultadoTexto) {
        this.resultadoTexto = resultadoTexto;
    }
}
