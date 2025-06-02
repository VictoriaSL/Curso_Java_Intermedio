package s3_ejemplo_03;

import java.util.List;

public class Sucursal {
    //ATRIBUTOS
    private final String nombre;
    //LISTA
    private final List<Pedido> pedidos;

    //CONSTRUCTOR
    public Sucursal(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }

    //GETTERS
    public List<Pedido> getPedidos() { return pedidos; }
    public String getNombre() { return nombre; }
}