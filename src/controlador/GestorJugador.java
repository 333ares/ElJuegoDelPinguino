package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import modelo.Foca;
import modelo.Inventario;
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

	public GestorJugador(Jugador jugador, Tablero tablero) {
		this.tablero = tablero;
		this.jugadorActual = jugador;
	}

	public void jugadorUsaItem(String nombreItem) {
		if (jugadorActual == null || jugadorActual.getPinguino() == null
				|| jugadorActual.getPinguino().getInv() == null) {
			System.out.println("Error: Jugador o inventario no inicializado");
			return;
		}

		Inventario inventario = jugadorActual.getPinguino().getInv();

		if (!inventario.contieneItem(nombreItem)) {
			System.out.println("No tienes el item: " + nombreItem);
			return;
		}

		System.out.println("Usando " + nombreItem + "...");

		switch (nombreItem) {
		case "pez":
			if (inventario.getCantidad("pez") > 0) {
				inventario.quitarItem("pez");
				jugadorActual.setProtegidoDelOso(true);
				System.out.println("Has usado un pez. Estás protegido del Oso durante este turno.");
			}
			break;

		case "bola de nieve":
			if (inventario.getCantidad("bola de nieve") > 0) {
				inventario.quitarItem("bola de nieve");
				System.out.println("Has usado una bola de nieve. Selecciona un jugador para retroceder.");
				// Lógica para seleccionar otro jugador (pendiente implementar)
			}
			break;

		case "dado rápido":
			if (inventario.getCantidad("dado rápido") > 0) {
				inventario.quitarItem("dado rápido");
				Random random = new Random();
				int movRapido = random.nextInt(6) + 5; // 5-10
				actualizarMovimientoJugador(jugadorActual, movRapido);
				System.out.println("Has usado un dado rápido. Avanzas " + movRapido + " casillas.");
			}
			break;

		case "dado lento":
			if (inventario.getCantidad("dado lento") > 0) {
				inventario.quitarItem("dado lento");
				Random rand = new Random();
				int movLento = rand.nextInt(3) + 1; // 1-3
				actualizarMovimientoJugador(jugadorActual, movLento);
				System.out.println("Has usado un dado lento. Avanzas " + movLento + " casillas.");
			}
			break;

		default:
			System.out.println("Item no reconocido: " + nombreItem);

		}
	}

	private Item buscarItemEnInventario(Inventario inventario, String nombreItem) {
		for (Item item : inventario.getLista()) {
			if (item.getNombre().equals(nombreItem) && item.getCantidad() > 0) {
				return item;
			}
		}
		return null;
	}

	public void actualizarMovimientoJugador(Jugador j, int movimientos) { // Mueve al jugador en el tablero.
		int nuevaPosicion = j.getPosicion() + movimientos;
		if (nuevaPosicion >= 0 && nuevaPosicion < 50) { // El tablero tiene 50 casillas (posiciones 0 a 49).
			j.setPosicion(nuevaPosicion);

			tablero.getCasillas()[nuevaPosicion].realizarAccion(j);
			// Al llegar a una casilla, se ejecuta su acción asociada (realizarAccion(j)).
		} else {
			System.out.println("El movimiento es inválido. El jugador no se moverá.");
		}
	}

	public void jugadorFinalizaTurno() {
		jugadorActual.setProtegidoDelOso(false);
		System.out.println(jugadorActual.getNombre() + " ha finalizado su turno.");
	}

	public void cambiarJugadorActual() {
		// No es necesario si solo hay un jugador
	}

	public void pinguinoEvento(Pinguino p) {

	}

	public void pinguinoGuerra(Pinguino p) {

	}

	public void focaInteractua(Pinguino p, Foca f) {

	}
}