package controlador;

import java.util.ArrayList;

import javafx.application.Platform;
import modelo.Agujero;
import modelo.Casilla;
import modelo.Evento;
import modelo.Jugador;
import modelo.Oso;
import modelo.Pinguino;
import modelo.Tablero;
import modelo.Trineo;

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
	public void actualizarMovimientoJugador(Jugador j, int movimientos) {
		  int nuevaPosicion = j.getPosicion() + movimientos;
		    
		    // Verificar límites del tablero
		    if (nuevaPosicion < 0) nuevaPosicion = 0;
		    if (nuevaPosicion >= tablero.getCasillas().length) {
		        nuevaPosicion = tablero.getCasillas().length - 1;
		    }
		    
		    j.setPosicion(nuevaPosicion);
		    
		    // Ejecutar acción de la casilla
		    Casilla casillaActual = tablero.getCasillas()[nuevaPosicion];
		    casillaActual.realizarAccion(j);
	

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
