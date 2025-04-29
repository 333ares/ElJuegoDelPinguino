package modelo;

public class Agujero extends Casilla {

	public Agujero(int posicion) {
		super(posicion);
		
	}

	public void realizarAccion(Jugador j) {
		System.out.println("¡Caíste en un agujero! Retrocedes 5 casillas.");
        int nuevaPosicion = j.getPosicion() - 5;
        if (nuevaPosicion < 0) nuevaPosicion = 0;
        j.setPosicion(nuevaPosicion);

	}

}