package controlador;

import modelo.Casilla;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;

public class GestorTablero {
    private Tablero tablero;

    public GestorTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void actualizarMovimientoJugador(Jugador j, int movimientos) {
    	// Este método actualiza la posición del jugador en el tablero y activa el efecto de la casilla correspondiente.
        int nuevaPosicion = j.getPosicion() + movimientos;
        if (nuevaPosicion >= 0 && nuevaPosicion < tablero.getCasillas().length) {
            j.setPosicion(nuevaPosicion);
            tablero.getCasillas()[nuevaPosicion].realizarAccion(j); 
        }
    }

	public void ejecutarCasilla (Pinguino p, Casilla c) {
		
	}
	
	public void actualizarFinalTurno() {
		
	}
	
	public void actualizarPantalla() {
		
	}
}
