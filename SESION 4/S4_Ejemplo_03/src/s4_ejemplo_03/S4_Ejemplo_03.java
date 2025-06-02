package s4_ejemplo_03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class S4_Ejemplo_03 {


    public static void main(String[] args) {
        
        System.out.println("Iniciando consultas a servicios externos...\n");

        //COMPLETABLE-FUTURE: Permite consultas asincrónicas y composición de resultados.
        CompletableFuture<String> climaFuture = obtenerClima();
        CompletableFuture<String> traficoFuture = obtenerTrafico();

        //Combina ambas consultas en un solo reporte
        //THEN-COMBINE: Combina resultados de dos tareas independientes.
        CompletableFuture<Void> reporteFinal = climaFuture.thenCombine(traficoFuture, 
            (clima, trafico) -> {
                return "Reporte del dia:\n- Clima: " + clima + "\n- Trafico: " + trafico;
            })
             //THEN-ACCEPT: Ejecuta una acción final sobre el resultado combinado.
             // 📤 Imprimir reporte
            .thenAccept(System.out::println) 
                
             //EXCEPTIONALLY: Maneja errores en el flujo asincrónico.
             // ❌ Manejo de errores
            .exceptionally(ex -> { 
                System.out.println("Error al generar el reporte: " + ex.getMessage());
                return null;
            });

        // Esperar que todo termine
        reporteFinal.join();
    }

    //Simula consulta a un servicio de clima
    public static CompletableFuture<String> obtenerClima() {
        
        //SUPPLYASYNC: Ejecuta una tarea que devuelve un valor de forma asincrónica.
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Consultando clima...");
            dormir(3); // Latencia simulada
            return "Soleado, 25 C";
        });
    }

    // 🚗 Simula consulta a un servicio de tráfico
    public static CompletableFuture<String> obtenerTrafico() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Consultando trafico...");
            dormir(2); // Latencia simulada
            return "Moderado en el centro.";
        });
    }

    // 💤 Método auxiliar para simular latencia
    public static void dormir(int segundos) {
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
