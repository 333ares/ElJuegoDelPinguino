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


                // Crear jugador
                Jugador jugador1 = new Jugador(0, "Jugador 1", "Azul", new Pinguino(new Inventario(new ArrayList<>())));
                tablero.setJugadorActual(jugador1); // Establece el jugador actual

                System.out.println("Partida iniciada con 1 jugador.");
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
