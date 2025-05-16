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
		   if (!j.isProtegidoDelOso()) {
	            System.out.println("¡El oso te atrapó! Vuelves al inicio.");
	            j.setPosicion(0); // Envía al jugador al inicio
	        } else {
	            System.out.println("¡Usaste un pez para protegerte del oso!");
	            j.setProtegidoDelOso(false); // Consume la protección
	        }
	}

}