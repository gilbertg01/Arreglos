package laboratorio.Pruebas.Entidades;

import java.util.UUID;

public class PruebaDTO {
    private UUID id;
    private String nombrePrueba;
    private double costo;
    private boolean tieneResultado;

    public PruebaDTO(Prueba prueba, boolean tieneResultado) {
        this.id = prueba.getIdPrueba();
        this.nombrePrueba = prueba.getNombrePrueba();
        this.costo = prueba.getCosto();
        this.tieneResultado = tieneResultado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isTieneResultado() {
        return tieneResultado;
    }

    public void setTieneResultado(boolean tieneResultado) {
        this.tieneResultado = tieneResultado;
    }
}
