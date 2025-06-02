package s2_ejemplo_01;

public class S2_Ejemplo_01 {

    public static void main(String[] args) {
        //THREAD: hilos
        Thread hilo1 = new Thread(new Tarea("Tarea 1"));
        Thread hilo2 = new Thread(new Tarea("Tarea 2"));
        
        //START: Inicia la ejecucion de los hilos.
        hilo1.start(); 
        hilo2.start(); 
        
        //IMPRIME EL CONTENIDO DE LOS HILOS
        System.out.println("Hilos iniciados desde el hilo principal: " +
                           Thread.currentThread().getName());
        
    }
    
}
