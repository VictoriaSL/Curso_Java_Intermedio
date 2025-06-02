package s2_reto_01;

// Subsistema de navegación

import java.util.concurrent.Callable;

class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "NAVEGACION: trayectoria corregida con exito.";
    }
}