package s2_reto_02;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

// Clase que representa el recurso médico crítico
class RecursoMedico {
    
    //ATRIBUTO
    private final String nombre;
    
    //SIMULACION DE CONCURRENCIA
    private final ReentrantLock lock = new ReentrantLock();

    //CONSTRUCTOR
    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }
    
    //SIMULACION DE ENTRADA, TIEMPO DE USO Y SALIDA
    public void usar(String profesional) {
        lock.lock();
        try {
            System.out.println(" * " + profesional + " ha ingresado a " + nombre);
            //TIEMPO DE ESPERA
            Thread.sleep(1000);
            System.out.println(" - " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}

public class S2_Reto_02 {
    public static void main(String[] args) {
        
        System.out.println("Iniciando acceso a la Sala de cirugia...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugia");

        // Lista de profesionales
        String[] profesionales = {
            "Dra. Sanchez",
            "Dr. Gomez",
            "Enf. Ramirez",
            "Dra. Lopez",
            "Dr. Perez"
        };

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (String profesional : profesionales) {
            executor.execute(() -> salaCirugia.usar(profesional));
        }

        executor.shutdown();
    }
}