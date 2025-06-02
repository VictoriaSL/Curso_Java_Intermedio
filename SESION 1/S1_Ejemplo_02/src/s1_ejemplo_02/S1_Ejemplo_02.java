package s1_ejemplo_02;

import java.util.List;

public class S1_Ejemplo_02 {
    
    
    // Método flexible: acepta cualquier tipo que sea Componente o subclase de Componente
    //? (wildcard) Representa cualquier tipo desconocido.
    public static void imprimirComponentes(List<? extends Componente> componentes) {
        for (Componente c : componentes) {
            System.out.println("Inspeccionando componente: " + c.getNombre());
        }
    }
    
     public static void main(String[] args) {
        List<Motor> motores = List.of(new Motor("Motor Falcon 9"), new Motor("Motor Raptor"));
        List<Ala> alas = List.of(new Ala("Ala Delta"), new Ala("Ala Supersonica"));

        //Metodo acepta ambos tipos gracias a la wildcard con extends
        imprimirComponentes(motores);
        imprimirComponentes(alas);
    }
    
}
