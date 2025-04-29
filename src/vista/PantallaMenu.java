package vista;

import java.util.Scanner;

public class PantallaMenu {
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Nueva Partida");
            System.out.println("2. Cargar Partida");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Iniciando nueva partida...");
                    // Lógica para iniciar nueva partida
                    break;
                case 2:
                    System.out.println("Cargando partida...");
                    // Lógica para cargar partida
                    break;
                case 3:
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
   
    }
}
