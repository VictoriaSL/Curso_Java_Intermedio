
package s2_ejemplo_02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class S2_Ejemplo_02 {

     public static void main(String[] args) throws InterruptedException, ExecutionException {
         
        //EXECUTOR-SERVICE: Gestiona un conjunto de hilos reutilizables.
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //CALLABLE: Area que puede devolver un resultado y lanzar excepciones.
        Callable<String> pedido1 = () -> {
            //SIMULA TIPO DE ESPERA
            Thread.sleep(1200); 
            return "PEDIDO #1 entregado en 1.2 segundos";
        };

        Callable<String> pedido2 = () -> {
            Thread.sleep(800);
            return "PEDIDO #2 entregado en 0.8 segundos";
        };

        Callable<String> pedido3 = () -> {
            Thread.sleep(1500);
            return "PEDIDO #3 entregado en 1.5 segundos";
        };
        
        //FUTURE: Envia la tarea al pool de los hilos.
        System.out.println("Procesando pedidos...");
        Future<String> r1 = executor.submit(pedido1);
        Future<String> r2 = executor.submit(pedido2);
        Future<String> r3 = executor.submit(pedido3);
        
        //IMPRIME LOS ELEMENTOS DE LOS PEDIDOS
        //GET: Obtiene y espera los resultados de una tarea.
        System.out.println(r1.get());
        System.out.println(r2.get());
        System.out.println(r3.get());
        
        //SHUTDOWN: Detiene los hilos al terminar una tarea.
        executor.shutdown();
        System.out.println("Todos los pedidos fueron procesados.");
    }
    
}
