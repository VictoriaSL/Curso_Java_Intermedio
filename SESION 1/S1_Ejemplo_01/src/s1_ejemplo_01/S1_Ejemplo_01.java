package s1_ejemplo_01;

public class S1_Ejemplo_01 {

    public static void main(String[] args) {

        Almacen<String> almacenRopa = new Almacen<>();
        System.out.println("¿Almacen de ropa vacio? " + almacenRopa.estaVacio());
        almacenRopa.guardarProducto("Camisa");
        almacenRopa.mostrarTipoProducto();

        Almacen<Integer> almacenNumeros = new Almacen<>();
        almacenNumeros.guardarProducto(42);
        almacenNumeros.mostrarTipoProducto();

        Almacen<String> almacenAlimentos = new Almacen<>();
        almacenAlimentos.guardarProducto("Manzana");
        almacenAlimentos.mostrarTipoProducto();

        System.out.println("\nProductos recuperados:");
        System.out.println("Ropa: " + almacenRopa.obtenerProducto());
        System.out.println("Numero: " + almacenNumeros.obtenerProducto());
        System.out.println("Alimento: " + almacenAlimentos.obtenerProducto());
    }
    
}
