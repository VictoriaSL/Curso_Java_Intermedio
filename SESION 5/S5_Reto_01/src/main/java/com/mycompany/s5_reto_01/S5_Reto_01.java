package com.mycompany.s5_reto_01;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

public class S5_Reto_01 {

   public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // SENSOR DE TRAFICO
        Flux<String> trafico = Flux.interval(Duration.ofMillis(500))
                // DATO SIMULADO 0-100
                .map(tick -> random.nextInt(101)) 
                .filter(congestion -> congestion > 70)
                .map(congestion -> "ALERTA: Congestion del " + congestion + "% en Avenida Solar")
                .doOnNext(msg -> System.out.println(msg));

        // CONTAMINACION
        Flux<String> contaminacion = Flux.interval(Duration.ofMillis(600))
                 // DATO SIMULADO 0-80 
                .map(tick -> random.nextInt(81))
                .filter(pm -> pm > 50)
                .map(pm -> "ALERTA: Contaminacion alta (PM2.5: " + pm + " ug/m3)")
                .doOnNext(msg -> System.out.println(msg));

        // ACCIDENTES (PRIORIDADES)
        List<String> prioridades = Arrays.asList("Baja", "Media", "Alta");
        Flux<String> accidentes = Flux.interval(Duration.ofMillis(800))
                .map(tick -> prioridades.get(random.nextInt(3)))
                .filter(prioridad -> prioridad.equals("Alta"))
                .map(prioridad -> "EMERGENCIA VIAL: Accidente con prioridad " + prioridad)
                .doOnNext(msg -> System.out.println(msg));

        // TRENES
        Flux<String> trenes = Flux.interval(Duration.ofMillis(700))
                // DATO SIMULADO 0-10 MINUTOS
                .map(tick -> random.nextInt(11)) 
                .filter(retraso -> retraso > 5)
                .map(retraso -> "Tren con retraso critico: " + retraso + " minutos")
                .doOnNext(msg -> System.out.println(msg));

        // SEMAFORO
        List<String> estados = Arrays.asList("Verde", "Amarillo", "Rojo");
        AtomicInteger contadorRojos = new AtomicInteger(0);
        Flux<String> semaforos = Flux.interval(Duration.ofMillis(400))
                .map(tick -> estados.get(random.nextInt(3)))
                .map(estado -> {
                    if (estado.equals("Rojo")) {
                        int count = contadorRojos.incrementAndGet();
                        if (count >= 3) {
                            contadorRojos.set(0);
                            return "Semaforo en Rojo detectado 3 veces seguidas en cruce Norte";
                        }
                    } else {
                        contadorRojos.set(0);
                    }
                    return null;
                })
                .filter(msg -> msg != null)
                .doOnNext(msg -> System.out.println(msg));

        //SENSORES CON BACKPRESSURE
        Flux<String> traficoCritico = Flux.interval(Duration.ofMillis(500))
                .map(tick -> random.nextInt(101)) 
                //SIMULACION BACKPRESSURE CON BUFFER
                .onBackpressureBuffer(10) 
                .filter(congestion -> congestion > 70)
                .map(congestion -> "ALERTA!!!: Congestion del " + congestion + "% en Avenida Solar")
                .doOnNext(msg -> System.out.println(msg)); 

        //CONTAMINACION CON BACKPRESSURE
        Flux<String> contaminacionCritica = Flux.interval(Duration.ofMillis(600))
                .map(tick -> random.nextInt(81)) 
                .filter(pm -> pm > 50)
                .map(pm -> "️ALERTA: Contaminacion alta (PM2.5: " + pm + " ug/m3)")
                .doOnNext(msg -> System.out.println(msg)); 

        // ACCIDENTES CON BACKPRESSURE
        Flux<String> accidentesCriticos = Flux.interval(Duration.ofMillis(800))
                .map(tick -> prioridades.get(random.nextInt(3)))
                .filter(prioridad -> prioridad.equals("Alta"))
                .map(prioridad -> "EMERGENCIA: Accidente con prioridad " + prioridad)
                .doOnNext(msg -> System.out.println(msg));

        // TRENES CON BACKPRESSURE
        Flux<String> trenesCriticos = Flux.interval(Duration.ofMillis(700))
                .map(tick -> random.nextInt(11))
                .onBackpressureBuffer(10) 
                .filter(retraso -> retraso > 5)
                .map(retraso -> "Tren con retraso critico: " + retraso + " minutos")
                .doOnNext(msg -> System.out.println(msg)); 

        AtomicInteger contadorRojos2 = new AtomicInteger(0);
        Flux<String> semaforosCriticos = Flux.interval(Duration.ofMillis(400))
                .map(tick -> estados.get(random.nextInt(3)))
                .map(estado -> {
                    if (estado.equals("Rojo")) {
                        int count = contadorRojos2.incrementAndGet();
                        if (count >= 3) {
                            contadorRojos2.set(0);
                            return "Semaforo en Rojo detectado 3 veces seguidas en cruce Norte";
                        }
                    } else {
                        contadorRojos2.set(0);
                    }
                    return null;
                })
                .filter(msg -> msg != null)
                .doOnNext(msg -> System.out.println(msg));

        // CONBINACION DE EVENTOS CRITICOS
        Flux<String> eventosCriticos = Flux.merge(
                traficoCritico,
                contaminacionCritica,
                accidentesCriticos,
                trenesCriticos,
                semaforosCriticos
        );

        // DETECTAR EVENTOS CRITICOS
        eventosCriticos.buffer(Duration.ofMillis(200))
                .filter(lista -> lista.size() >= 3)
                .subscribe(lista -> {
                    lista.forEach(System.out::println);
                    System.out.println("ALERTA GLOBAL: Multiples eventos criticos detectados en Meridian Prime");
                });

        // SUBCRIPCION DE CADA FLUJO
        traficoCritico.subscribe();
        contaminacionCritica.subscribe();
        accidentesCriticos.subscribe();
        trenesCriticos.subscribe();
        semaforosCriticos.subscribe();
        //TIEMPO DE ESPERA
        Thread.sleep(20000); 
    }
}
