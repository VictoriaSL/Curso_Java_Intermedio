package s2_ejemplo_01;

//RUNNABLE: Interfaz funcional para definir tareas que pueden ser ejecutadas por un hilo.
public class Tarea implements Runnable {
    
    //ATRIBUTO
    private String nombre;
    
    //CONSTRUCTOR
    public Tarea(String nombre) {
        this.nombre = nombre;
    }
    
    //METODO HEDERADO POR RUNNABLE 
    @Override
    public void run() {
        //EJECUTA 5 VECES LA SIMULACIÓN DE TAREAS
        for (int i = 1; i <= 5; i++) {
            System.out.println("Ejecutando " + nombre + " - Iteracion " + i +
                               " - Hilo: " + Thread.currentThread().getName());
            try {
                //SIMULACION DE TIEMPO DE ESPERA
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nombre + " fue interrumpido");
            }
        }
    }
}