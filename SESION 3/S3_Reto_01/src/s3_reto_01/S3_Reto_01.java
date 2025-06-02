package s3_reto_01;
import java.util.*;
import java.util.stream.*;


public class S3_Reto_01 {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("Ana", "domicilio", "555-1234"),
            new Pedido("Luis", "local", null),
            new Pedido("Carlos", "domicilio", null),
            new Pedido("Marta", "domicilio", "555-5678")
        );

        pedidos.stream()
            .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
            .map(Pedido::getTelefono)
            .flatMap(Optional::stream)
            .map(tel -> "Confirmacion enviada al numero: " + tel)
            .forEach(System.out::println);
    }
}
