package s3_reto_02;
import java.util.*;
import java.util.stream.*;

public class S3_Reto_02 {
    public static void main(String[] args) {
        
        //ELEMENTOS DE LISTA
        Sucursal centro = new Sucursal("Centro", Arrays.asList(
            new Encuesta("Ana", "El tiempo de espera fue largo", 2),
            new Encuesta("Luis", null, 4),
            new Encuesta("Carlos", "No me explicaron bien el tratamiento", 3)
        ));

        Sucursal norte = new Sucursal("Norte", Arrays.asList(
            new Encuesta("Marta", "La atencion fue buena, pero la limpieza puede mejorar", 3),
            new Encuesta("Pedro", null, 2),
            new Encuesta("Sofía", null, 5)
        ));
        
        List<Sucursal> sucursales = Arrays.asList(centro, norte);
        
        
        sucursales.stream()
            .flatMap(sucursal -> 
                sucursal.getEncuestas().stream()
                     //AQUI SE FILTRAN LOS QUE SON MENOR QUE 3
                    .filter(e -> e.getCalificacion() <= 3)
                     //TRANSFORMA COMENTARIO EN MENSAJE
                    .map(e -> e.getComentario()
                        .map(comentario -> "Sucursal " + sucursal.getNombre() + 
                            ": Seguimiento a paciente con comentario: \"" + comentario + "\"")
                    )
            )
            //SOLO MUESTRA LOS QUE NO SON NULLOS
            .flatMap(Optional::stream)
            .forEach(System.out::println);
    }
}