package modelo;

public class Oso extends Casilla {

	/*
	 * Clase que hereda de Casilla y representa una casilla especial de tipo Oso. Su
	 * constructor recibe una posición inicial. Implementa realizarAccion(), que
	 * cuando un jugador cae en ella: 1) muestra un mensaje
	 * ("El oso ha atrapado al pingüino"), y 2) reinicia su posición al inicio del
	 * tablero (posición 0). Ejemplo claro de herencia y polimorfismo en el modelo
	 * del juego.
	 * 
	 */
	public Oso(int posicion) {
		super(posicion);
	}

	public void realizarAccion(Jugador j) {
		// Acción que realizara el oso.
		System.out.println("El oso ha atrapado al pingüino. Vuelves al inicio.");
		j.setPosicion(0); // Retorna al inicio

	}

}