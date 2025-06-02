package s3_ejemplo_03;

import java.util.Optional;

public class Pedido {
    
    //ATRIBUTOS
    private final String cliente;
    private final String tipoEntrega; // "domicilio" o "local"
    private final String telefono; // Puede ser null

    //CONSTRUCTOR
    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    //GETTERS
    public String getCliente(){ return cliente; }
    public String getTipoEntrega() { return tipoEntrega; }
    public Optional<String> getTelefono() { return Optional.ofNullable(telefono); }
}