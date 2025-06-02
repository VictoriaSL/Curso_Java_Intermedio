package s4_reto_01;

import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class S4_Reto_01 {

    //SIMULA EL CALCULO DE LA RUTA
    public static CompletableFuture<String> calcularRuta() {
        
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculando ruta...");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al calcular la ruta");
            }
            return "Centro -> Norte";
        });
    }

    // SIMULA LA ESTIMACION DE LA TARIFA
    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Estimando tarifa...");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al estimar la tarifa");
            }
            //SIMULACION DE UN ERROR
            if (ThreadLocalRandom.current().nextBoolean()) throw new RuntimeException("Demanda muy alta");
            return 75.50;
        });
    }

    public static void main(String[] args) {
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) ->
            "Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
        )
        .exceptionally(ex -> "Ocurrio un error: " + ex.getMessage())
        .thenAccept(System.out::println);
        
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}