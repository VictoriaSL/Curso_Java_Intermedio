package s3_ejemplo_03;

import java.util.List;

public class S3_Ejemplo_03 {

     public static void main(String[] args) {
         
        //SE AGREGAN ELEMENTOS: Lista de sucursales con sus pedidos
        List<Sucursal> sucursales = List.of(
            new Sucursal("Centro", List.of(
                new Pedido("Juan", "domicilio", "555-1234"),
                new Pedido("María", "local", null)
            )),
            new Sucursal("Norte", List.of(
                new Pedido("Carlos", "domicilio", null),
                new Pedido("Luisa", "domicilio", "555-5678")
            )),
            new Sucursal("Sur", List.of(
                new Pedido("Esteban", "domicilio", "555-9012"),
                new Pedido("Diana", "domicilio", null)
            )),
            new Sucursal("Este", List.of(
                new Pedido("Andrés", "local", null),
                new Pedido("Sofía", "domicilio", "555-3456")
            ))
        );

        System.out.println("Confirmaciones de pedidos globales:");

        //Procesamos todos los pedidos de todas las sucursales
        sucursales.stream()
            .flatMap(sucursal -> 
                sucursal.getPedidos().stream()
                     // 🔍 Filtrar entregas a domicilio
                    .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio")) 
                     // Combinar pedido + sucursal
                    .map(pedido -> new Confirmacion(pedido, sucursal.getNombre())) 
            )
            // 🔍 Filtrar Optional con valor
            .filter(conf -> conf.getPedido().getTelefono().isPresent()) 
            .map(conf -> {
                String telefono = conf.getPedido().getTelefono().get();
                return "Pedido de " + conf.getPedido().getCliente() + 
                       " en la sucursal " + conf.getSucursal() + 
                       " confirmado al numero: " + telefono;
            })
            .forEach(System.out::println); // 📤 Imprimir mensajes
    }

    // Clase auxiliar para transportar Pedido + Sucursal juntos
    static class Confirmacion {
        private final Pedido pedido;
        private final String sucursal;

        public Confirmacion(Pedido pedido, String sucursal) {
            this.pedido = pedido;
            this.sucursal = sucursal;
        }

        public Pedido getPedido() { return pedido; }
        public String getSucursal() { return sucursal; }
    }
    
}
