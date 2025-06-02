package s1_ejemplo_01;

//---  CLASE GENERICA ---//

//Clase genérica que adapta su comportamiento al tipo especificado en tiempo de compilación.
public class Almacen<T> {
    
    // 	Parámetro de tipo genérico
    private T producto;

    // Guarda un producto de cualquier tipo : Método que recibe un parámetro del tipo genérico.
    public void guardarProducto(T producto) {
        this.producto = producto;
        System.out.println("Producto guardado: " + producto);
    }

    // Devuelve el producto almacenado : Devuelve el objeto almacenado del tipo genérico T.
    public T obtenerProducto() {
        return producto;
    }

    // Verifica si el almacén está vacío : Método que verifica si el almacén está vacío.
    public boolean estaVacio() {
        return producto == null;
    }

    // Muestra el tipo de producto almacenado : Muestra el tipo real del producto almacenado usando reflection.
    public void mostrarTipoProducto() {
        if (producto != null) {
            System.out.println("Tipo de producto almacenado: " + producto.getClass().getSimpleName());
        } else {
            System.out.println("El almacén está vacío.");
        }
    }
}