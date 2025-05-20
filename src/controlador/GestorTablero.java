package controlador;

import java.util.ArrayList;
import modelo.Casilla;
import modelo.Jugador;
import modelo.Oso;
import modelo.Tablero;

public class GestorTablero {
	private Tablero tablero; // Declara un campo privado tablero de tipo Tablero, que almacenará la
								// referencia al tablero que esta clase gestionará.
	private ArrayList<Integer> posicionesAgujeros; // Para que GestorTablero se encarge de la busqueda de los agujeros.

	public GestorTablero(Tablero tablero) {
		this.tablero = tablero;
		this.posicionesAgujeros = new ArrayList<>();
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

	/*
	 * Actualiza la posición del jugador en el tablero después de moverse y activa
	 * los efectos de la casilla correspondiente.
	 */
	public void actualizarMovimientoJugador(Jugador jugador, int pasos) {
		
		// Calculamos la nueva posición sumando los pasos a la posición actual
		int posicionTemporal = jugador.getPosicion() + pasos;

		// Verificamos que la posición no sea menor a 0 (primera casilla)
		int posicionMinima = 0;

		// Verificamos que la posición no exceda el tamaño del tablero
		int posicionMaxima = tablero.getCasillas().length - 1;

		// Aseguramos que la nueva posición esté dentro de los límites
		int nuevaPosicion = posicionTemporal;

		if (posicionTemporal > posicionMaxima) {
		    nuevaPosicion = posicionMaxima;
		}
		if (posicionTemporal < posicionMinima) {
		    nuevaPosicion = posicionMinima;
		}
		
		// Actualizamos la posición del jugador
		jugador.setPosicion(nuevaPosicion);

		// Obtenemos la casilla donde cayó el jugador
		Casilla casillaDestino = tablero.getCasillas()[nuevaPosicion];

		// Ejecutamos la acción correspondiente a la casilla
		casillaDestino.realizarAccion(jugador);

		 // Verificar si el jugador ha alcanzado la última casilla
	    if (jugador.getPosicion() == tablero.getCasillas().length - 1) {
	        String mensaje = "¡Has finalizado la partida!";
	        if (jugador.getGestorMensajes() != null) {
	            jugador.getGestorMensajes().agregarMensaje(mensaje);
	        }
	    }

	    // Verificamos si cayó en un Oso y tenía protección
	    if (casillaDestino instanceof Oso) { // instanceof = operador de Java que comprueba si un objeto es una instancia de una clase específica o de sus subclases
	        if (jugador.isProtegidoDelOso()) {
	            // Si tenía protección, la consumimos
	            jugador.setProtegidoDelOso(false);
	        
		    }	
		}
	}

	public int buscarAgujeroAnterior(int posicionActual) {
		/*
		 * Esta función busca la posición de un agujero que esté antes de la posición
		 * actual (posicionActual).
		 * 
		 * Comienza a buscar desde la posición anterior a la actual y se mueve hacia
		 * atrás.
		 * 
		 * Si encuentra un agujero en la lista posicionesAgujeros, devuelve esa
		 * posición.
		 * 
		 * Si no encuentra ningún agujero antes de llegar al inicio, devuelve 0.
		 * 
		 */
		 // Buscar desde la posición anterior hasta el inicio
	    for (int i = posicionActual - 1; i >= 0; i--) {
	        if (posicionesAgujeros.contains(i)) {
	            return i; // Devuelve el primer agujero encontrado
	        }
	    }
	    // Si no encuentra agujeros, devuelve la posición actual (no mover)
	    return posicionActual;

	}

	public Tablero getTablero() {
		return this.tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void setPosicionesAgujeros(ArrayList<Integer> posiciones) {
		this.posicionesAgujeros = posiciones;
	}
}