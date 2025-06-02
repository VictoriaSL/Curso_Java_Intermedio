package s4_ejemplo_01;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class S4_Ejemplo_01 {

     public static void main(String[] args) throws InterruptedException {
         
        System.out.println("Ejecucion concurrente con ExecutorService:");
        
        //CONCURRENCIA CON 3 hilos
        //EXCECUTOR-SERVICE: Manejador de hilos que permite controlar la concurrencia.
        ExecutorService executor = Executors.newFixedThreadPool(3); 

        //Concurrencia: más órdenes
        executor.submit(() -> procesarOrden("Orden 1 (Pizza)", 3));
        executor.submit(() -> procesarOrden("Orden 2 (Pasta)", 2));
        executor.submit(() -> procesarOrden("Orden 3 (Hamburguesa)", 4));
        executor.submit(() -> procesarOrden("Orden 4 (Ensalada)", 1));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS); // Espera que terminen las órdenes concurrentes

        System.out.println("Ejecucion asincronica con CompletableFuture:");

        //COMPLETABLE-FUTURE: Herramienta para manejar asincronía, permitiendo componer, encadenar 
        //y manejar tareas no bloqueantes.
        // 🔹 Asincronía: más órdenes
        CompletableFuture<Void> ordenAsync1 = CompletableFuture.runAsync(() -> procesarOrden("Orden 5 (Sopa)", 2));
        CompletableFuture<Void> ordenAsync2 = CompletableFuture.runAsync(() -> procesarOrden("Orden 6 (Tacos)", 3));
        CompletableFuture<Void> ordenAsync3 = CompletableFuture.runAsync(() -> procesarOrden("Orden 7 (Sandwich)", 2));

        CompletableFuture.allOf(ordenAsync1, ordenAsync2, ordenAsync3).join(); // Espera que todas las órdenes asincrónicas terminen
    }

    // Simula procesamiento de una orden con tiempo de espera (latencia)
    public static void procesarOrden(String orden, int segundos) {
        System.out.println("Procesando " + orden);
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(" * " + orden + " completada");
    }
    
}
