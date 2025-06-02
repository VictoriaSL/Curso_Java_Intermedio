package s2_ejemplo_03;

public class CuentaBancaria {
    
    //ATRUIBUTO
    private int saldo = 1000;
    
    //SYNCHRONIZED: Bloquea automáticamente el objeto al entrar al método
    public synchronized void retirar(String nombre, int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(nombre + " ESTA RETIRANDO $" + cantidad);
            saldo -= cantidad;
            System.out.println(nombre + " RETIRO COMPLETO!!!. Saldo restante: $" + saldo);
        } else {
            System.out.println(nombre + " NO SE PUDO :c fondos insuficientes.");
        }
    }

    //GETTER
    public int getSaldo() {
        return saldo;
    }
}