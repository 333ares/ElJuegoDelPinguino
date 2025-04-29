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
                    Tablero tablero = new Tablero(new Casilla[50], new ArrayList<>(), 0, null);
                    tablero.mostrarTablero(); // Aquí se muestra el tablero


                    // Pedir número de jugadores
                    System.out.print("Ingrese número de jugadores (2-4): ");
                    int numJugadores = s.nextInt();
                    if (numJugadores >= 2 && numJugadores <= 4) {
                        for (int i = 0; i < numJugadores; i++) {
                            // Aquí código para añadir jugadores al tablero
                            // Ejemplo:
                            Jugador jugador = new Jugador (0, "Jugador " + (i + 1), "Color", new Pinguino(new Inventario(new ArrayList<>())));
                            jugador.setTablero(tablero);
                            tablero.getJugadores().add(jugador);
                        }
                        System.out.println("Partida iniciada con " + numJugadores + " jugadores.");
                    } else {
                        System.out.println("Número de jugadores debe ser entre 2 y 4.");
                    }
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
