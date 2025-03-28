package Modelo;

import java.util.ArrayList;

public class Tablero {

	ArrayList<Casilla> casillas = new ArrayList<Casilla>();
	
	// CONSTRUCTOR
	public Tablero(ArrayList<Casilla> casillas) {
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
