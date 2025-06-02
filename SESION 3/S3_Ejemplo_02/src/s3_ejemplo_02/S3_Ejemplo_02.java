package s3_ejemplo_02;

import java.util.List;
import java.util.Optional;

public class S3_Ejemplo_02 {

   public static void main(String[] args) {
       
       //SE AGREGAN LOS ELEMENTOS A LA LISTA
        List<Paciente> pacientes = List.of(
            new Paciente("Ana", 34, false, "ana@example.com"),
            new Paciente("Luis", 70, true, null),
            new Paciente("Marta", 45, true, "marta@example.com"),
            new Paciente("Pedro", 28, false, null)
        );

        System.out.println("Correos electronicos disponibles:");

        //Iniciamos el stream sobre la lista de pacientes
        pacientes.stream() 
            // 🔄 map transforma Paciente → Optional<String> (correo)
            .map(Paciente::getCorreo)
            // 🔍 filter permite solo los Optionals que tienen valor (no vacíos)
            .filter(Optional::isPresent) 
            // 📥 map extrae el valor del Optional
            .map(Optional::get)
            // 📤 forEach imprime los valores finales
            .forEach(System.out::println);
        
        System.out.println("");
        System.out.println("Pacientes en observacion (mayores de 40 años):");

        pacientes.stream()
            .filter(p -> p.isEnObservacion() && p.getEdad() > 40) // 🔍 filter aplica condición booleana
            .forEach(System.out::println); // 📤 forEach imprime los pacientes filtrados
    }
    
}
