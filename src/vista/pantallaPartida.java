package vista;

import java.util.ArrayList;

import controlador.GestorTablero;
import modelo.Casilla;
import modelo.Tablero;

public class pantallaPartida {

	public void iniciarPartida() {
	    Tablero tablero = new Tablero(new Casilla[50], new ArrayList<>(), 0, null);
	    GestorTablero gestorTablero = new GestorTablero(tablero);
	    // Lógica para añadir jugadores y comenzar el juego
	}

}
