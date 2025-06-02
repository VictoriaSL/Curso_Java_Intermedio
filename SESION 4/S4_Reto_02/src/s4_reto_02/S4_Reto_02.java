package s4_reto_02;

import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class S4_Reto_02 {

    //SIMULACION PROBABILIDAD: 80%
    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar pista...");
            }
            boolean disponible = ThreadLocalRandom.current().nextInt(100) < 80;
            System.out.println("Pista disponible: " + disponible);
            return disponible;
        });
    }

    //SIMULACION PROBABILIDAD: 85%
    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar clima");
            }
            boolean favorable = ThreadLocalRandom.current().nextInt(100) < 85;
            System.out.println("Clima favorable: " + favorable);
            return favorable;
        });
    }

      //SIMULACION PROBABILIDAD: 90%
    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar trafico aereo");
            }
            boolean despejado = ThreadLocalRandom.current().nextInt(100) < 90;
            System.out.println("Trafico aereo despejado: " + despejado);
            return despejado;
        });
    }

    //SIMULACION PROBABILIDAD: 95%
    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar personal en tierra");
            }
            boolean disponible = ThreadLocalRandom.current().nextInt(100) < 95;
            System.out.println("Personal disponible: " + disponible);
            return disponible;
        });
    }

    public static void main(String[] args) {
        
        System.out.println("Verificando condiciones para aterrizaje...");
        System.out.println("");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(pista, clima, trafico, personal);

        //SIMULACION
        allChecks.thenRun(() -> {
            try {
                boolean resultadoFinal = pista.get() && clima.get() && trafico.get() && personal.get();
                if (resultadoFinal) {
                    System.out.println("Aterrizaje autorizado: todas las condiciones Optimas.");
                } else {
                    System.out.println("Aterrizaje denegado: condiciones no Optimas.");
                }
            } catch (Exception e) {
                System.out.println("Aterrizaje denegado: error en la verificación (" + e.getMessage() + ")");
            }
        })
        //ERRORES
        .exceptionally(ex -> {
            System.out.println("Aterrizaje denegado: error inesperado (" + ex.getMessage() + ")");
            return null;
        });

        //TIEMPO DE ESPERA
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}