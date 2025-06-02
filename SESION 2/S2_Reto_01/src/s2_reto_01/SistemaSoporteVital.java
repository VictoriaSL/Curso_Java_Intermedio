package s2_reto_01;

import java.util.concurrent.Callable;


// Subsistema de soporte vital
class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(800);
        return "SOPORTE VITAL: presion y oxigeno dentro de parametros normales.";
    }
}