package laboratorio.Paciente.Excepciones;

public class DuplicateDocumentException extends RuntimeException {
    public DuplicateDocumentException(String mensaje) {
        super(mensaje);
    }
}
