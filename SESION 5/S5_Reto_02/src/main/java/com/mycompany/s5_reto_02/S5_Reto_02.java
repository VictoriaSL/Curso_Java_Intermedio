package com.mycompany.s5_reto_02;

import java.time.Duration;
import java.util.Random;
import reactor.core.publisher.Flux;

public class S5_Reto_02 {
 private static final Random random = new Random();

    // CLASE PARA INGRESAR PACIENTES
    static class PatientData {
        int patientId;
        int heartRate;
        String bloodPressure; 
        int oxygenLevel;
        boolean isCritical;
        String criticalReason; 
    //CONSTRUCTOR
        PatientData(int patientId, int heartRate, String bloodPressure, int oxygenLevel) {
            this.patientId = patientId;
            this.heartRate = heartRate;
            this.bloodPressure = bloodPressure;
            this.oxygenLevel = oxygenLevel;
            this.isCritical = checkCritical();
            this.criticalReason = this.isCritical ? getCriticalReason() : null;
        }

    // GENERACION DE DATOS DE PACIENTES
        static PatientData generateRandomData(int patientId) {
            int hr = 40 + random.nextInt(100);
            int sys = 80 + random.nextInt(80);
            int dia = 50 + random.nextInt(50);
            int spo2 = 80 + random.nextInt(20); 

            return new PatientData(patientId, hr, sys + "/" + dia, spo2);
        }

        // VERIFICACION DE LOS DATOS SI SON CRITICOS
        private boolean checkCritical() {

            if (heartRate < 50 || heartRate > 120) return true;
            try {
                String[] pa = bloodPressure.split("/");
                int sys = Integer.parseInt(pa[0]);
                int dia = Integer.parseInt(pa[1]);
                if ((sys < 90 || dia < 60) || (sys > 140 || dia > 90)) return true;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Error parsing blood pressure: " + bloodPressure);
            }
            if (oxygenLevel < 90) return true;
            return false;
        }

        //OBTIENE UNA RAZON
        private String getCriticalReason() {
            StringBuilder reason = new StringBuilder();
            if (heartRate < 50 || heartRate > 120) {
                reason.append("FC citica: ").append(heartRate).append(" bpm; ");
            }
            try {
                String[] pa = bloodPressure.split("/");
                int sys = Integer.parseInt(pa[0]);
                int dia = Integer.parseInt(pa[1]);
                if ((sys < 90 || dia < 60) || (sys > 140 || dia > 90)) {
                    reason.append("PA critica: ").append(bloodPressure).append(" mmHg; ");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            }
            if (oxygenLevel < 90) {
                reason.append("SpO2 baja: ").append(oxygenLevel).append("%");
            }
            if (reason.length() > 0 && reason.toString().endsWith("; ")) {
                reason.setLength(reason.length() - 2);
            }
            return reason.toString();
        }

        @Override
        public String toString() {
            return String.format("Paciente %d - FC: %d, PA: %s, SpO2: %d",
                    patientId, heartRate, bloodPressure, oxygenLevel);
        }

        public String getCriticalAlert() {
             return String.format("Paciente %d - %s", patientId, criticalReason);
        }
    }

    public static void main(String[] args) {
        System.out.println("Iniciando simulacion de UCI...");

        //SIMULACION DE FLUJOS
        Flux<PatientData> patient1Flux = Flux.interval(Duration.ofMillis(300))
                .map(tick -> PatientData.generateRandomData(1));

        Flux<PatientData> patient2Flux = Flux.interval(Duration.ofMillis(300))
                .map(tick -> PatientData.generateRandomData(2));

        Flux<PatientData> patient3Flux = Flux.interval(Duration.ofMillis(300))
                .map(tick -> PatientData.generateRandomData(3));

        //FUSIONAR FLUJOS
        Flux<PatientData> mergedFlux = Flux.merge(patient1Flux, patient2Flux, patient3Flux);

        //SE FILTRAN LOS CASOS CRITICOS
        mergedFlux
                .filter(data -> data.isCritical)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(
                        criticalData -> System.out.println(criticalData.getCriticalAlert()), 
                        error -> System.err.println("Ocurrió un error: " + error),
                        () -> System.out.println("Simulación completada.") 
                );
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
