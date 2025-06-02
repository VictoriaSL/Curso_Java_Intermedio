package s3_ejemplo_01;

public class Paciente {

    //VARIABLES GLOBALES
    private final String nombre;
    private final int edad;
    private final boolean enObservacion;

    //CONSTRUCTOR
    public Paciente(String nombre, int edad, boolean enObservacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.enObservacion = enObservacion;
    }

    //GETTERS
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public boolean isEnObservacion() { return enObservacion; }

    // Método toString
    @Override
    public String toString() {
        return nombre + " (Edad: " + edad + ", Observación: " + enObservacion + ")";
    }
}