package interfaz;

import java.util.*;

public class Interfaz {

    /** 
     * Método que muestra el mensaje de bienvenida y las opciones disponibles.
     */
    public void inicio() {
        asteriscos(65);
        System.out.println("\n\tBIENVENIDO\n\n"
                + "\tPulse 'enter' para acceder al sistema de ventas.\n\n"
                + "\tTeclee 'admin' para acceder al modo administrador.\n\n"
                + "\tTeclee 'exit' para salir y apagar el sistema.\n\n");
        asteriscos(65);
    }
    
    
    /** 
     * Método que imprime una línea de asteriscos.
     * @param numeroAsteriscos Número de asteriscos a imprimir.
     */
    public void asteriscos(int numeroAsteriscos) {
        for(int i = 0; i < numeroAsteriscos; i++) {
            System.out.print("*");
        }
        System.out.println("\n");
    }
    
    /** 
     * Método que imprime una línea de guiones.
     * @param numeroGuiones Número de guiones a imprimir.
     */
    public void guiones(int numeroGuiones) {
        for(int i = 0; i < numeroGuiones; i++) {
            System.out.print("-");
        }
        System.out.println("\n");
    }
    
    /** 
     * Método que espera a que el usuario pulse Enter para continuar.
     */
    public void enter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPulsa ENTER para continuar.");
        sc.nextLine();
    }
    
}
