package s2_reto_01;

import java.util.concurrent.Callable;

// Subsistema de control térmico
class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1200);
        return "CONTROL TERMICO: temperatura estable (22 C).";
    }
}
