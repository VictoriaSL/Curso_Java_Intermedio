package s2_reto_01;
import java.util.concurrent.*;

public class S2_Reto_01 {
    public static void main(String[] args) throws Exception {
        System.out.println("Simulacion de mision espacial iniciada...");
        
        //EXECUTORSERVICE
        ExecutorService executor = Executors.newFixedThreadPool(4);
        
        //ENVIO DE TAREAS CON SUBMIT
        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> soporte = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> comunicaciones = executor.submit(new SistemaComunicaciones());

        //SE IMPRIMEN EN ORDEN 
        System.out.println(comunicaciones.get());
        System.out.println(soporte.get());
        System.out.println(termico.get());
        System.out.println(nav.get());
        
        //SE CIERRAN LOS HILOS
        executor.shutdown();
        System.out.println("TODOS LOS SISTEMAS REPORTAN ESTADO OPERATIVO");
    }
}