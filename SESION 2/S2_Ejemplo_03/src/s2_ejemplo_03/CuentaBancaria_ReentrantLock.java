package s2_ejemplo_03;
import java.util.concurrent.locks.ReentrantLock;

public class CuentaBancaria_ReentrantLock  {
    
    //ATRIBUTO
    private int saldo = 1000;
    
    //REENTRANTLOCK: Permite controlar manualmente el bloqueo/desbloqueo, y usar condiciones
    private final ReentrantLock lock = new ReentrantLock();

    
    public void retirar(String nombre, int cantidad) {
        //LOCK: Cierra.
        lock.lock();
        try {
           
            if (saldo >= cantidad) {
            System.out.println(nombre + " ESTA RETIRANDO $" + cantidad);
            saldo -= cantidad;
            System.out.println(nombre + " RETIRO COMPLETO!!!. Saldo restante: $" + saldo);
        } else {
            System.out.println(nombre + " NO SE PUDO :c fondos insuficientes.");
        }
        } finally {
            lock.unlock(); // ⚠️ ¡Siempre liberar el lock!
        }
    }

    public int getSaldo() {
        return saldo;
    }
}
