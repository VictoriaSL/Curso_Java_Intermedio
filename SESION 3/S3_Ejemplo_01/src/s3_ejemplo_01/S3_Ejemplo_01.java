package s3_ejemplo_01;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class S3_Ejemplo_01 {

   public static void main(String[] args) {

        //AGREGAR ELEMENTOS A LISTA.
        List<Paciente> pacientes = List.of(
            new Paciente("Ana", 34, false),
            new Paciente("Luis", 70, true),
            new Paciente("Marta", 45, true),
            new Paciente("Pedro", 28, false)
        );

        //LAMBDA: Predicate para pacientes mayores de 60
        Predicate<Paciente> mayoresDe60 = p -> p.getEdad() > 60;

        //Method REFERENCE: Predicate para pacientes en observación
        Predicate<Paciente> enObservacion = Paciente::isEnObservacion;

        //Composición funcional con Predicate.and()
        Predicate<Paciente> casoCritico = mayoresDe60.and(enObservacion);

        System.out.println("Pacientes en estado critico:");

        //Uso de STREAM para recorrer la lista de pacientes
        pacientes.stream() // ← Stream inicia aquí
            .filter(casoCritico) // ← filter aplica Predicate<Paciente>
            .forEach(System.out::println); // ← forEach aplica método por referencia

        //FUNCTION: transforma un Paciente en un String resumen
        Function<Paciente, String> resumen = p ->
            "-Paciente: " + p.getNombre() + " | Edad: " + p.getEdad();

        System.out.println("\n Resumen general:");

        pacientes.stream() // ← Stream API
            .map(resumen) // ← map aplica Function<Paciente, String>
            .forEach(System.out::println); // ← Acción final (output en consola)
    }
    
}
