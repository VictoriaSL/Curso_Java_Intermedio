package s2_ejemplo_03;

public class S2_Ejemplo_03 {

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();
        CuentaBancaria_ReentrantLock cuenta2 = new CuentaBancaria_ReentrantLock();
        
        //THREAD.CURRENTTHREAD().GETNAME(): Permite identificar el hilo activo.
        Runnable tarea = () -> {
            String nombreHilo = Thread.currentThread().getName();
            cuenta.retirar(nombreHilo, 300);
        };
        //THREAD.CURRENTTHREAD().GETNAME(): Permite identificar el hilo activo.
        Runnable tarea2 = () -> {
            String nombreHilo = Thread.currentThread().getName();
            cuenta2.retirar(nombreHilo, 300);
        };
            
        //ELEMENTOS DEL HILO 1
        Thread t1 = new Thread(tarea, "Astronauta-1");
        Thread t2 = new Thread(tarea, "Astronauta-2");
        Thread t3 = new Thread(tarea, "Astronauta-3");
        
        //ELEMENTOS DEL HILO 2
        Thread t11 = new Thread(tarea2, "Piloto-1");
        Thread t22 = new Thread(tarea2, "Piloto-2");
        Thread t33 = new Thread(tarea2, "Piloto-3");
        
        //INICIA EL HILO 1
        t1.start();
        t2.start();
        t3.start();
        
         //INICIA EL HILO 2
        t11.start();
        t22.start();
        t33.start();
    }
    
}
