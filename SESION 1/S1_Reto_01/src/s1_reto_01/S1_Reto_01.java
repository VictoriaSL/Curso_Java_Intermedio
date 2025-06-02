package s1_reto_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//--------CLASE ABSTRACTA--------//
abstract class OrdenProduccion {
    
    //ATRIBUTOS
    protected String codigo;
    protected int cantidad;
    
    //COSNTRUCTOR
    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }
    //MÉTODO ABSTRACTO MOSTRAR-RESUMEN
    public abstract void mostrarResumen();
}

//--------SUBCLASES ORDEN EN MASA--------//
class OrdenMasa extends OrdenProduccion {
    
    public OrdenMasa(String codigo, int cantidad) {
        super(codigo, cantidad);
    }
    
    @Override
    public void mostrarResumen() {
        System.out.println("OrdenMasa - Codigo: " + codigo + " - Cantidad: " + cantidad);
    }
}

//--------SUBCLASES ORDEN PERSONALIZADA--------//
class OrdenPersonalizada extends OrdenProduccion {
    //ATRIBUTO 
    private String cliente;
    
    //CONSTRUCTOR 
    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }
    //GETTER
    public String getCliente() {
        return cliente;
    }
    //MÉTODO MOSTRAR EL RESUMEN
    @Override
    public void mostrarResumen() {
        System.out.println("OrdenPersonalizada - Codigo: " + codigo + " - Cantidad: " + cantidad + " - Cliente: " + cliente);
    }
    //MÉTODO AGREGAR - COSTO
    public void agregarCosto(int costo) {
        System.out.println("Orden " + codigo + " ajustada con costo adicional de $" + costo);
    }
}

//--------SUBCLASES ORDEN PROTOTIPO--------//
class OrdenPrototipo extends OrdenProduccion {
    
    //ATRIBUTO
    private String faseDesarrollo;
    
    //CONSTRUCTOR
    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }
    //METODO PARA MOSTRAR
    @Override
    public void mostrarResumen() {
        System.out.println("OrdenPrototipo - Codigo: " + codigo + " - Cantidad: " + cantidad + " - Fase: " + faseDesarrollo);
    }
}

public class S1_Reto_01 {

    //MÉTODO GENERICO PARA MOSTRAR CUALQUIER TIPO DE ORDEN
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("Ordenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    //MÉTODO PARA PROCESAR SOLO ORDENES PERSONALIZADAS
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("Procesando Ordenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                ((OrdenPersonalizada) obj).agregarCosto(costoAdicional);
            }
        }
    }

    // DESAFIO ADICIONAL c:! CONTAR ORDENES
    public static void contarOrdenes(List<OrdenProduccion> todas) {
        //VARIABLES 
        int masa = 0, pers = 0, prot = 0;
        
        for (OrdenProduccion o : todas) {
            if (o instanceof OrdenMasa) masa++;
            else if (o instanceof OrdenPersonalizada) pers++;
            else if (o instanceof OrdenPrototipo) prot++;
        }
        System.out.println("Resumen total de ordenes:");
        System.out.println("Produccion en masa: " + masa);
        System.out.println("Personalizadas: " + pers);
        System.out.println("Prototipos: " + prot);
    }
////--------CLASE PRINCIPAL--------//
    public static void main(String[] args) {
        //SE AGREGAN ELEMENTOS A LAS LISTAS
        List<OrdenMasa> ordenesMasa = Arrays.asList(
            new OrdenMasa("A123", 500),
            new OrdenMasa("A124", 750)
        );
        List<OrdenPersonalizada> ordenesPersonalizadas = Arrays.asList(
            new OrdenPersonalizada("P456", 100, "ClienteX"),
            new OrdenPersonalizada("P789", 150, "ClienteY")
        );
        List<OrdenPrototipo> ordenesPrototipo = Arrays.asList(
            new OrdenPrototipo("T789", 10, "Diseno"),
            new OrdenPrototipo("T790", 5, "Pruebas")
        );
        //LLAMADA DE METODOS
        mostrarOrdenes(ordenesMasa);
        System.out.println("");
        mostrarOrdenes(ordenesPersonalizadas);
        System.out.println("");
        mostrarOrdenes(ordenesPrototipo);
        System.out.println("");

        // SE USA UNA LISTA DE SUPERTIPO PARA PROCESAR LAS ORDENES PERSONALIZADAS
        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(ordenesMasa);
        todas.addAll(ordenesPersonalizadas);
        todas.addAll(ordenesPrototipo);
        procesarPersonalizadas(todas, 200);

        //SE LLAMA METODO 
        contarOrdenes(todas);
    }
} 