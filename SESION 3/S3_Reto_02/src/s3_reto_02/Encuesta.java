package s3_reto_02;
import java.util.Optional;

public class Encuesta {
    
    //ATRIBUTOS
    private String paciente;
    private String comentario; 
    private int calificacion;

    //CONSTRUCTOR
    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    //GETTERS
    public int getCalificacion() {
        return calificacion;
    }
    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }
}
