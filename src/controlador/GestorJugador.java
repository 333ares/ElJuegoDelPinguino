package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import modelo.Foca;
import modelo.Item;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;
import modelo.Jugador;

public class GestorJugador {
	/*
	 * La clase GestorJugador actúa como un controlador que gestiona las acciones
	 * principales de un jugador en un juego, vinculando las mecánicas del modelo
	 * (como ítems, movimientos y personajes como pingüinos y focas). Sus funciones
	 * clave son:
	 * 
	 * Usar ítems (protección contra un oso, retroceder jugadores rivales, dados
	 * para moverse más rápido o lento),
	 * 
	 * Mover al jugador por un tablero de 50 casillas, activando efectos al caer en
	 * ellas,
	 * 
	 * Finalizar turnos (reiniciando estados temporales como protecciones), y
	 * 
	 * Placeholders para futuras interacciones con eventos, combates o focas (aún no
	 * implementadas). Esta clase funciona como intermediaria entre el jugador, su
	 * inventario, y el tablero, aplicando reglas del juego y efectos de ítems.
	 * 
	 * 
	 */
	private Jugador jugadorActual;
	private Tablero tablero;

	public GestorJugador(Jugador jugador1, Jugador jugador2, Tablero tablero) {
		this.tablero = tablero;
		this.jugadorActual = jugador1;
		tablero.getJugadores().add(jugador1);
		tablero.getJugadores().add(jugador2);
	}

	public List<Jugador> getOtrosJugadores() {
		List<Jugador> otrosJugadores = new ArrayList<>();
		for (Jugador jugador : tablero.getJugadores()) {
			if (!jugador.equals(jugadorActual)) {
				otrosJugadores.add(jugador);
			}
		}
		return otrosJugadores;
	}

	public void jugadorUsaItem(Jugador j, String nombreItem) {
		// Este método permite al jugador usar un item
		for (Item item : j.getPinguino().getInv().getLista()) {// Busca el ítem en el inventario del jugador
			if (item.getNombre().equals(nombreItem)) {// Implementa el efecto del ítem
				System.out.println("Usando " + nombreItem + "...");
				if ("pez".equals(nombreItem)) {// Remueve el ítem del inventario y activa una protección contra el Oso.
					j.getPinguino().getInv().quitarItem(item);
					System.out.println("Has usado un pez. Estás protegido del Oso durante este turno.");
					j.setProtegidoDelOso(true);
				} else if ("bola de nieve".equals(nombreItem)) {// Permite seleccionar otro jugador para hacerlo
																// retroceder 3 casillas.
					System.out.println("Has usado una bola de nieve. Selecciona un jugador para retroceder.");
					// Lógica para seleccionar otro jugador y hacerle retroceder

					ArrayList<Jugador> otrosJugadores = new ArrayList<>();
					for (Jugador otroJugador : j.getTablero().getJugadores()) {// Filtra otros jugadores excluyendo al
																				// actual
																				// (j.getTablero().getJugadores()).
						if (!otroJugador.equals(j)) {
							otrosJugadores.add(otroJugador);
						}
					}

					if (!otrosJugadores.isEmpty()) {
						System.out.println("Seleccione un jugador:");
						for (int i = 0; i < otrosJugadores.size(); i++) {
							System.out.println((i + 1) + ". " + otrosJugadores.get(i).getNombre());
						}
						// Suponiendo que solo hay dos jugadores
						Jugador objetivo = null;
						// Siempre hay dos jugadores, así que si el jugador actual es el primero, el
						// objetivo es el segundo y viceversa
						if (jugadorActual.equals(tablero.getJugadores().get(0))) {
							objetivo = tablero.getJugadores().get(1);
						} else {
							objetivo = tablero.getJugadores().get(0);
						}

						int nuevaPosicion = objetivo.getPosicion() - 3; // Retroceder 3 casillas
						if (nuevaPosicion < 0)
							nuevaPosicion = 0;
						objetivo.setPosicion(nuevaPosicion);
						System.out.println(objetivo.getNombre() + " retrocede 3 casillas.");
					} else {
						System.out.println("No hay otros jugadores para afectar.");
					}

					j.getPinguino().getInv().quitarItem(item);
				} else if ("dado rápido".equals(nombreItem)) {// Avanza 5-10 casillas.
					System.out.println("Has usado un dado rápido. Avanzas entre 5-10 casillas.");
					Random random = new Random();
					int movimientos = random.nextInt(6) + 5; // 5-10
					j.moverPosicion(movimientos);
					j.getPinguino().getInv().quitarItem(item);
				} else if ("dado lento".equals(nombreItem)) { // Avanza 1-3 casillas.
					System.out.println("Has usado un dado lento. Avanzas entre 1-3 casillas.");
					Random random = new Random();
					int movimientos = random.nextInt(3) + 1; // 1-3
					j.moverPosicion(movimientos);
					j.getPinguino().getInv().quitarItem(item);
				}
				break;
			}
		}

	}

	public void jugadorSeMueve(Jugador j, int movimientos) {// Mueve al jugador en el tablero.
		int nuevaPosicion = j.getPosicion() + movimientos;
		if (nuevaPosicion >= 0 && nuevaPosicion < 50) {// El tablero tiene 50 casillas (posiciones 0 a 49).
			j.setPosicion(nuevaPosicion);

			j.getTablero().getCasillas()[nuevaPosicion].realizarAccion(j);
			// Al llegar a una casilla, se ejecuta su acción asociada (realizarAccion(j)).
		} else {
			System.out.println("El movimiento es inválido. El jugador no se moverá.");
		}

	}

	public void jugadorFinalizaTurno(Jugador j) {
		j.setProtegidoDelOso(false);
		System.out.println(j.getNombre() + " ha finalizado su turno.");
		cambiarJugadorActual(); // Cambiar al siguiente jugador
	}

	public void cambiarJugadorActual() {
		int indiceActual = tablero.getJugadores().indexOf(jugadorActual);
		int siguienteIndice = (indiceActual + 1) % tablero.getJugadores().size();
		jugadorActual = tablero.getJugadores().get(siguienteIndice);
		System.out.println("El jugador actual ahora es: " + jugadorActual.getNombre());
	}

	public void pinguinoEvento(Pinguino p) {

	}

	public void pinguinoGuerra(Pinguino p) {

	}

	public void focaIntercatua(Pinguino p, Foca f) {

	}
}
