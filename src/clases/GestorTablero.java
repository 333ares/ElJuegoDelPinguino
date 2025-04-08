package clases;

import java.util.ArrayList;

public class GestorTablero {

	ArrayList<Casilla> casillas = new ArrayList<Casilla>();
	
	// CONSTRUCTOR
	public GestorTablero(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}
	
	// FUNCIONES
	public void generarTablero() {
		
	}
	
	public void mostrarTablero() {
		
	}
	
	// GETTERS Y SETTERS
	public ArrayList<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(ArrayList<Casilla> casillas) {
		this.casillas = casillas;
	}
}
