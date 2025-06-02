package s3_reto_01;

import java.util.Optional;

public class Pedido {
    
    //ATRIBUTOS
    private String cliente;
    private String tipoEntrega; 
    private String telefono;    

    //CONSTRUCTOR
    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    //GETTERS
    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }
}