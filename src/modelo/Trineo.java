package modelo;

public class Trineo extends Casilla {

	public Trineo(int posicion) {
		super(posicion);
	}

	public void realizarAccion(Jugador j) {
		 System.out.println("¡Enhorabuena! Has encontrado un trineo. Avanzas 10 casillas.");
	        int nuevaPosicion = j.getPosicion() + 10;
	        if (nuevaPosicion >= 50) nuevaPosicion = 49;
	        j.setPosicion(nuevaPosicion);

	}
	
	
}
