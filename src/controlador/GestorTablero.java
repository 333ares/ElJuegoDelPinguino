package controlador;

import java.util.ArrayList;

import modelo.Casilla;
import modelo.Jugador;
import modelo.Pinguino;

public class GestorTablero {

	public void actualizarMovimientoJugador(Jugador j, int movimientos) {
		   int nuevaPosicion = j.getPosicion() + movimientos;
		    if (nuevaPosicion >= 0 && nuevaPosicion < tablero.getCasillas().length) {
		        j.moverPosicion(nuevaPosicion);
		        tablero.getCasillas()[nuevaPosicion].activarEfecte(j.getPinguino());
		    }

	}
	
	public void ejecutarCasilla (Pinguino p, Casilla c) {
		
	}
	
	public void actualizarFinalTurno() {
		
	}
	
	public void actualizarPantalla() {
		
	}
}
