
package s1_reto_02;
import java.util.*;
import java.util.function.Predicate;


// CLASE ABSTRACTA 
abstract class MaterialCurso {
    //ATRIBUTOS
    protected String titulo;
    protected String autor;
    //CONSTRUCTOR
    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    //GETTER
    public String getAutor() {
        return autor;
    }
    //METODO ABSTRACTO PARA MOSTRAR LOS DETALLES
    public abstract void mostrarDetalle();
}

// ------------SUBCLASE VIDEO------------
class Video extends MaterialCurso {
    
    //ATRIBUTO, LA DURACION SE MIDE EN MINUTOS
    private int duracion; 
    //CONTRUCTOR 
    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }
    //GETTER
    public int getDuracion() {
        return duracion;
    }
    //METODO ABSTRACTO HEDERADO DE CLASE MATERIAL CURSO
    @Override
    public void mostrarDetalle() {
        System.out.println("-VIDEO: " + titulo + " -AUTOR: " + autor + " -DURACION: " + duracion + " min");
       }
}

// ------------SUBCLASE ARTICULO------------
class Articulo extends MaterialCurso {
    
    //ATRIBUTO
    private int palabras;
    //CONSTRUCTOR
    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }
    //METODO ABSTRACTO HEDERADO DE MATERIAL CURSO.
    @Override
    public void mostrarDetalle() {
        System.out.println("-ARTICULO: " + titulo + " -AUTOR: " + autor + " -PALABRAS: " + palabras);
    }
}

// ------------SUBCLASE EJERCICIO------------
class Ejercicio extends MaterialCurso {
    //ATRIBUTO
    private boolean revisado;
    //CONSTRUCTOR 
    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false;
    }
    //METODO QUE VALIDA SI ESTA REVISADO
    public boolean isRevisado() {
        return revisado;
    }
    //SETTER
    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }
    //METODO ABSTRACTO HEDERADO DE MATERIAL CURSO.
    @Override
    public void mostrarDetalle() {
        System.out.println("-EJERCICIO: " + titulo + " -AUTOR: " + autor + " -REVISADO: " + revisado);
    }
}

// METODOS GENERICOS
public class S1_reto_02 {
    //MOSTRAR DETALLES
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("MATERIALES DE CURSO: ");
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
        System.out.println();
    }

    // DURACION DE VIDEOS(AQUI SE SUMAN LOS MINUTOS) 
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video v : lista) {
            total += v.getDuracion();
        }
        System.out.println("DURACION TOTAL: " + total + " minutos\n");
    }

    // MARCA LOS EJERCISIOS COMO REVISADOS
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                Ejercicio e = (Ejercicio) obj;
                e.setRevisado(true);
                System.out.println("EJERCICIO: '" + e.titulo + "' MARCADO COMO REVISADO.");
            }
        }
        System.out.println();
    }

    //DESAFIO  ADICIONAL: Método genérico que filtre materiales por autor usando Predicate<MaterialCurso>.
    public static <T extends MaterialCurso> void filtrarPorAutor(List<T> lista, Predicate<T> filtro) {
        System.out.println("MATERIALES FILTRADOS POR AUTOR:");
        for (T m : lista) {
            if (filtro.test(m)) {
                m.mostrarDetalle();
            }
        }
        System.out.println();
    }

    //METODO MAIN 
    public static void main(String[] args) {
        //AGREGAR ELEMENTOS EN LA LISTA
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a Java", "Mario", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Ana", 1200));
        materiales.add(new Articulo("Tipos de datos", "Luis", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Luis"));
        materiales.add(new Ejercicio("Condicionales", "Mario"));

        // MOSTRAR LOS MATERIALES
        mostrarMateriales(materiales);
         
        // FILTRO PARA LOS VIDEOS PARA CONTAR DURACION
        List<Video> soloVideos = new ArrayList<>();
        for (MaterialCurso m : materiales) {
            if(m instanceof Video) soloVideos.add((Video) m);
        }
        contarDuracionVideos(soloVideos);

        //VIDEOS REVISADOS
        marcarEjerciciosRevisados(materiales);

        //DESAFIO ADICIONAL: filtrar por autor
        filtrarPorAutor(materiales, m -> m.getAutor().equals("Mario"));
    }
}