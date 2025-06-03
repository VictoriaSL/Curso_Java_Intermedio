package com.mycompany.s5_ejemplo_02;

import java.time.Duration;
import java.util.List;
import reactor.core.publisher.Flux;

public class S5_Ejemplo_02 {

  public static void main(String[] args) throws InterruptedException {
        
        // Ejemplo 1️⃣: Notificaciones en Universidad
        List<Usuario> usuarios = List.of(
            new Usuario("Ana", "Estudiante", "ana@uni.edu"),
            new Usuario("Carlos", "Profesor", "carlos@uni.edu"),
            new Usuario("Luisa", "Estudiante", "luisa@uni.edu"),
            new Usuario("Sofia", "Administrativo", "sofia@uni.edu")
        );

        Flux.fromIterable(usuarios)
            .filter(u -> u.getRol().equalsIgnoreCase("Estudiante")) // 🔍 Filtra estudiantes
            .map(u -> "Notificacion para: " + u.getNombre())     // 🔄 Transforma en mensaje
            .delayElements(Duration.ofMillis(500))
            .subscribe(m -> System.out.println("Universidad: " + m));

        // Ejemplo 2️⃣: Confirmación de entregas (logística)
        Flux.just("Pedido 1", "Pedido 2", "Pedido 3")
            .flatMap(pedido -> simularEntrega(pedido)) // 🔁 Cada pedido genera otro flujo (simula proceso asíncrono)
            .subscribe(m -> System.out.println("Logistica: " + m));

        // Ejemplo 3️⃣: Alertas de sensores
        Flux.just(18, 22, 35, 45) // 🔢 Temperaturas de sensores
            .filter(temp -> temp > 30) // 🔍 Filtra temperaturas altas
            .map(temp -> "Alerta: Temperatura alta: " + temp + " C")
            .subscribe(System.out::println);

        Thread.sleep(4000); // Esperar finalización
    }

    // 🔧 Simula proceso asíncrono para confirmación de entrega
    private static Flux<String> simularEntrega(String pedido) {
        return Flux.just(pedido + " confirmado")
                   .delayElements(Duration.ofMillis(700));
    }

    // Clase auxiliar
    static class Usuario {
        private final String nombre;
        private final String rol;
        private final String correo;

        public Usuario(String nombre, String rol, String correo) {
            this.nombre = nombre;
            this.rol = rol;
            this.correo = correo;
        }

        public String getNombre() { return nombre; }
        public String getRol() { return rol; }
    }
}
