package s2_reto_01;
import java.util.concurrent.Callable;

// Subsistema de comunicaciones
class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(600);
        return "COMUNICACIONES: enlace con estacion terrestre establecido.";
    }
}