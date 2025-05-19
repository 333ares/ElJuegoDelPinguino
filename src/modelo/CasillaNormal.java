package modelo;

public class CasillaNormal extends Casilla {

	/*
	 * La clase CasillaNormal es una implementación concreta de la clase abstracta
	 * Casilla. Representa una casilla normal en el tablero de juego, donde no
	 * ocurre ninguna acción especial cuando un jugador cae en ella. La
	 * implementación del método realizarAccion simplemente imprime un mensaje que
	 * indica que el jugador puede continuar su turno normalmente.
	 * 
	 */

	public CasillaNormal(int posicion) {
		super(posicion);
	}

	public void realizarAccion(Jugador j) {
		
		
	}

}