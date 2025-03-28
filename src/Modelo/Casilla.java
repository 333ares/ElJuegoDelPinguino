package Modelo;

import java.util.ArrayList;

public class Casilla {

	int posicion;
	int tipoCasilla;

	// CONSTRUCTOR
	public Casilla(int posicion, int tipoCasilla) {
		this.posicion = posicion;
		this.tipoCasilla = tipoCasilla;
	}

	// FUNCIONES
	public void aplicarEfecto(ArrayList<Jugador> jugadores) {

	}

	// GETTERS Y SETTERS
	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getTipoCasilla() {
		return tipoCasilla;
	}

	public void setTipoCasilla(int tipoCasilla) {
		this.tipoCasilla = tipoCasilla;
	}

}
