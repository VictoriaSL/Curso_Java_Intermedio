package s3_ejemplo_02;

import java.util.Optional;

public class Paciente {
    
    //ATRIBUTOS
    private final String nombre;
    private final int edad;
    private final boolean enObservacion;
    private final String correo; // Puede ser nulo

    //CONSTRUCTOR
    public Paciente(String nombre, int edad, boolean enObservacion, String correo) {
        this.nombre = nombre;
        this.edad = edad;
        this.enObservacion = enObservacion;
        this.correo = correo;
    }

    //GETTERS
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public boolean isEnObservacion() { return enObservacion; }

    //Método que devuelve Optional para evitar NullPointerException
    public Optional<String> getCorreo() {
        return Optional.ofNullable(correo);
    }
    
    //METODO TO STRING
    @Override
    public String toString() {
        return nombre + " (Edad: " + edad + ")";
    }
}
