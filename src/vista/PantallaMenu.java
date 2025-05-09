package vista;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Casilla;
import modelo.Inventario;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;

public class PantallaMenu {
    public void mostrarMenu() {
        Scanner s = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Nueva Partida");
            System.out.println("2. Cargar Partida");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = s.nextInt();

            switch (opcion) {
                case 1:
                    // Iniciar nueva partida
                    System.out.println("Iniciando nueva partida...");

                    // Crear tablero
                    Tablero tablero = new Tablero();
                    tablero.inicializarCasillas(); // Aquí se inicializan las casillas del tablero

                    // Crear jugadores
                    Jugador jugador1 = new Jugador(opcion, "Jugador 1", null, null);
                    Jugador jugador2 = new Jugador(opcion, "Jugador 2", null, null);
                    tablero.getJugadores().add(jugador1);
                    tablero.getJugadores().add(jugador2);

                    System.out.println("Partida iniciada con 2 jugadores.");
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
