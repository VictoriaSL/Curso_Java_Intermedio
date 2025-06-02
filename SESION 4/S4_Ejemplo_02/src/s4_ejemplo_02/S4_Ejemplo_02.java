package s4_ejemplo_02;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class S4_Ejemplo_02 {

    public static void main(String[] args) {
        
        System.out.println("Procesando pedido asinconicamente...\n");
        
        //COMPLETABLE-FUTURE: Permite crear tareas asincrónicas y encadenarlas
        CompletableFuture<Void> pedido = procesarPago()
                
             //Encadena y transforma el resultado
             //THEN-APPLY: Transforma el resultado de una tarea previa.
            .thenApply(pago -> generarFactura(pago))
                
             //Consume el resultado final
             //THEN-ACCEPT: Consume el resultado final sin devolver nada.
            .thenAccept(factura -> enviarNotificacion(factura)) 
                
             //Maneja errores
             //EXCEPTIONALLY: Maneja errores en cualquier parte del flujo asincrónico.
            .exceptionally(ex -> { 
                System.out.println("Error en el proceso: " + ex.getMessage());
                return null;
            });
        
        //Espera que termine todo el flujo
        pedido.join(); 
    }

    // Simula el procesamiento de un pago
    public static CompletableFuture<String> procesarPago() {
        //SUPPLYASYNC: Ejecuta una tarea de forma asincrónica y devuelve un valor.
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Procesando pago...");
            dormir(2);
            System.out.println("Pago confirmado");
            return "Pago#123";
        });
    }

    // Simula la generación de una factura
    public static String generarFactura(String pago) {
        System.out.println("Generando factura para " + pago + "...");
        dormir(1);
        String factura = "Factura#456";
        System.out.println("Factura generada: " + factura);
        return factura;
    }

    // 📧 Simula el envío de una notificación
    public static void enviarNotificacion(String factura) {
        System.out.println("Enviando notificacion por " + factura + "...");
        dormir(1);
        System.out.println("Notificacion enviada");
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
