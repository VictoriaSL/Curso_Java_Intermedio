package s3_reto_02;
import java.util.List;

public class Sucursal {
    //ATRIBUTOS
    private String nombre;
    //LISTA
    private List<Encuesta> encuestas;

    //CONSTRUCTOR
    public Sucursal(String nombre, List<Encuesta> encuestas) {
        this.nombre = nombre;
        this.encuestas = encuestas;
    }

    //GETTERS
    public String getNombre() {
        return nombre;
    }
    public List<Encuesta> getEncuestas() {
        return encuestas;
    }
}