package com.mycompany.s5_ejemplo_01;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import reactor.core.publisher.Flux;

public class S5_Ejemplo_01 {

   public static void main(String[] args) throws InterruptedException {

//----------------------STREAM API TRADICIONAL----------------------//
        List<String> usuarios = List.of("Ana", "Luis", "Marta", "Carlos");

        System.out.println("Enviando notificaciones con Stream API:");

        usuarios.forEach(S5_Ejemplo_01::enviarNotificacion);
        
//----------------------FLUX----------------------//
        System.out.println("Enviando notificaciones con Flux:");

        Flux<String> usuariosFlux = Flux.just("Ana", "Luis", "Marta", "Carlos")
                                        .delayElements(Duration.ofSeconds(1)); // Simula llegada gradual

        usuariosFlux.subscribe(usuario -> 
            System.out.println("Notificacion enviada a: " + usuario)
        );

        // Esperar que terminen las notificaciones reactivas
        Thread.sleep(5000);
    }

    private static void enviarNotificacion(String usuario) {
        try {
            TimeUnit.SECONDS.sleep(1); // Simula retraso en enviar la notificación
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("NOTIFICACION ENVIADA A: " + usuario);
    }
    
   
}
