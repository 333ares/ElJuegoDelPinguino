package modelo;

import controlador.GestorTablero;

public class Agujero extends Casilla {

	/*
	 * Esta clase Agujero representa una casilla especial en un tablero de juego.
	 * Cuando un jugador cae en esta casilla: Se imprime un mensaje de retroceso. Se
	 * busca la posición del último agujero anterior a la posición actual del
	 * jugador. El jugador se mueve a esa posición anterior. Esta implementación
	 * utiliza herencia de la clase Casilla y depende del gestor del tablero
	 * (GestorTablero) para obtener la posición del agujero anterior.
	 * 
	 */

	private GestorTablero gestor;

	public Agujero(int posicion, GestorTablero gestor) {
		super(posicion);
		this.gestor = gestor;
	}

	public void realizarAccion(Jugador j) {
		System.out.println("¡Agujero! Retrocedes al anterior.");
		int nuevaPos = gestor.buscarAgujeroAnterior(j.getPosicion());
		// Llama al método buscarAgujeroAnterior del gestor del tablero, pasándole la
		// posición actual del jugador (j.getPosicion()). Este método busca la posición
		// del último agujero anterior a la posición actual del jugador.
		j.setPosicion(nuevaPos);// Asigna el resultado (la nueva posición) a la variable nuevaPos.
	}
}