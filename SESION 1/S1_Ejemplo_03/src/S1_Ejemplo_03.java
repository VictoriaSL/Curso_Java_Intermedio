import java.util.*;

public class S1_Ejemplo_03 {

    // Superclase Cuenta
    static abstract class Cuenta {
        private final String titular;
        protected double saldo;

        public Cuenta(String titular, double saldoInicial) {
            this.titular = titular;
            this.saldo = saldoInicial;
        }

        public String getTitular() { return titular; }
        public double getSaldo() { return saldo; }

        public void mostrarEstado() {
            System.out.println("***" + titular + " - Saldo: $" + saldo);
        }
    }

    // Subclases de cuentas
    static class CuentaAhorro extends Cuenta {
        public CuentaAhorro(String titular, double saldoInicial) { super(titular, saldoInicial); }
    }

    static class CuentaCorriente extends Cuenta {
        public CuentaCorriente(String titular, double saldoInicial) { super(titular, saldoInicial); }
    }

    static class CuentaInversion extends Cuenta {
        public CuentaInversion(String titular, double saldoInicial) { super(titular, saldoInicial); }
    }

    // Método genérico para mostrar cuentas (wildcard extends)
    public static void mostrarCuentas(List<? extends Cuenta> cuentas) {
        System.out.println("Estado de cuentas:");
        cuentas.forEach(Cuenta::mostrarEstado);
    }

    // Método para procesar depósitos (wildcard super)
    public static void procesarDepositos(List<? super CuentaCorriente> cuentas, double cantidad) {
        System.out.println("Procesando depositos...");
        cuentas.forEach(c -> {
            if (c instanceof CuentaCorriente) {
                CuentaCorriente cc = (CuentaCorriente) c;
                cc.saldo += cantidad;
                System.out.println("Deposito de $" + cantidad + " en cuenta de " + cc.getTitular());
            }
        });
    }

    public static void main(String[] args) {
        List<CuentaAhorro> ahorros = List.of(
            new CuentaAhorro("Ana", 1500.0),
            new CuentaAhorro("Carlos", 2200.0)
        );

        List<CuentaCorriente> corrientes = List.of(
            new CuentaCorriente("Luis", 1200.0),
            new CuentaCorriente("Sofia", 1800.0)
        );

        List<CuentaInversion> inversiones = List.of(
            new CuentaInversion("Marta", 5000.0)
        );

        // 1️⃣ Mostrar cada tipo de cuenta
        mostrarCuentas(ahorros);              // Impresión 1-2
        mostrarCuentas(corrientes);           // Impresión 3-4
        mostrarCuentas(inversiones);          // Impresión 5

        // 2️⃣ Procesar depósitos en cuentas corrientes
        procesarDepositos(corrientes, 500.0); // Impresión 6-7

        // 3️⃣ Mostrar cuentas corrientes actualizadas
        mostrarCuentas(corrientes);           // Impresión 8-9

        System.out.println("Fin de la simulacion financiera."); // Impresión 10
    }
}