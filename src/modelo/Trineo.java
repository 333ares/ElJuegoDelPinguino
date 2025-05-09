package modelo;

public class Trineo extends Casilla {

	/*
	 * Hereda de Casilla y representa un beneficio en el tablero
	 * 
	 * Constructor establece posición usando clase padre
	 * 
	 * Al activarse (realizarAccion):
	 * 
	 * Anuncia: "¡Enhorabuena! Encontraste un trineo"
	 * 
	 * Beneficia: avanza 10 casillas al jugador
	 * 
	 * Controla límites (no pasa de casilla 49)
	 * 
	 * Ejemplo de casilla con efecto positivo mediante herencia
	 */

	public Trineo(int posicion) {
		super(posicion);
	}

	public void realizarAccion(Jugador j) {

		System.out.println("¡Evento aleatorio!");
		System.out.println("¡Enhorabuena! Has encontrado un trineo. Avanzas 10 casillas.");
		int nuevaPosicion = j.getPosicion() + 10;
		if (nuevaPosicion >= 50)
			nuevaPosicion = 49;
		j.setPosicion(nuevaPosicion);

	}

}
