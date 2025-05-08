package controlador;

import java.util.ArrayList;

import modelo.Casilla;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;

public class GestorTablero {
	private Tablero tablero; // Declara un campo privado tablero de tipo Tablero, que almacenará la
								// referencia al tablero que esta clase gestionará.
	private ArrayList<Integer> posicionesAgujeros; // Para que GestorTablero se encarge de la busqueda de los agujeros.

	public GestorTablero(Tablero tablero) {
		this.tablero = tablero;

	}

	/*
	 * Esta clase actúa como intermediario entre el modelo (Tablero, Jugador,
	 * Casilla) y otras partes del sistema. Su principal función es:
	 * 
	 * Gestionar los movimientos de los jugadores en el tablero
	 * 
	 * Verificar que los movimientos sean válidos
	 * 
	 * Ejecutar las acciones asociadas a las casillas cuando un jugador cae en ellas
	 * 
	 * 
	 */
	public void actualizarMovimientoJugador(Jugador j, int movimientos) {
		// Este método actualiza la posición del jugador en el tablero y activa el
		// efecto de la casilla correspondiente.
		int nuevaPosicion = j.getPosicion() + movimientos;// Calcula la nueva posición sumando los movimientos a la
															// posición actual del jugador.
		if (nuevaPosicion >= 0 && nuevaPosicion < tablero.getCasillas().length) {
			// Verifica si la nueva posición es válida (dentro de los límites del tablero).
			j.setPosicion(nuevaPosicion);// Si la posición es válida, actualiza la posición del jugador.
			tablero.getCasillas()[nuevaPosicion].realizarAccion(j); // Obtiene la casilla correspondiente a la nueva
																	// posición y ejecuta su acción sobre el jugador.
		} else {
			// Si el movimiento no es válido, muestra un mensaje de error y no realiza
			// ningún cambio.
			System.out.println("Movimiento inválido. El jugador no se mueve.");
		}

	}

	public int buscarAgujeroAnterior(int posicionActual) {
		/*
		 * Esta función busca la posición de un agujero que esté antes de la posición
		 * actual (posicionActual). Comienza a buscar desde la posición anterior a la
		 * actual y se mueve hacia atrás. Si encuentra un agujero en la lista
		 * posicionesAgujeros, devuelve esa posición. Si no encuentra ningún agujero
		 * antes de llegar al inicio, devuelve 0.
		 * 
		 */
		for (int i = posicionActual - 1; i >= 0; i--) {
			if (posicionesAgujeros.contains(i)) {
				return i;
			}
		}
		return 0;
	}

	public void ejecutarCasilla(Pinguino p, Casilla c) {

	}

	public void actualizarFinalTurno() {

	}

	public void actualizarPantalla() {

	}
}
