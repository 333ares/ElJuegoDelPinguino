package modelo;

import java.util.ArrayList;

public class Agujero extends Casilla {

	public Agujero(int posicion) {
		super(posicion);
		
	}

	public void realizarAccion(Jugador j) {
		System.out.println("¡Has caído en un agujero! Retrocederas al agujero anterior.");
	    int nuevaPosicion = buscarAgujeroAnterior(j.getPosicion());
	    j.setPosicion(nuevaPosicion);
	}


	private int buscarAgujeroAnterior(int posicionActual) {
	    ArrayList<Integer> posicionesAgujeros = new ArrayList<>();
	    // Llena la lista con posiciones de agujeros

	    for (int i = posicionActual - 1; i >= 0; i--) {
	        if (posicionesAgujeros.contains(i)) {
	            return i;
	        }
	    }
	    return 0; // Si no hay agujeros antes, retorna al inicio
	}


}