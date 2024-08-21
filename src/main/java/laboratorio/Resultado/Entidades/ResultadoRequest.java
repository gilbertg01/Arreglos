package laboratorio.Resultado.Entidades;

import java.util.UUID;

public class ResultadoRequest {

    private  Long facturaId;
    private Long pruebaId;
    private String resultadoTexto;

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public Long getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(Long pruebaId) {
        this.pruebaId = pruebaId;
    }

    public String getResultadoTexto() {
        return resultadoTexto;
    }

    public void setResultadoTexto(String resultadoTexto) {
        this.resultadoTexto = resultadoTexto;
    }
}
