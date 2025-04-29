package modelo;

public class Evento extends Casilla {

	String tipoEvento;

	// CONSTRUCTOR
	public Evento(int posicion) {
		super(posicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	// Define el comportamiento que ocurre cuando un jugador cae en una casilla de
	// evento.
	public void realizarAccion(Jugador j) {
		System.out.println("Evento aleatorio activado!");
	}
}
