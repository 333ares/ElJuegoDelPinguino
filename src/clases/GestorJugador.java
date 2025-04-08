package clases;

import java.util.ArrayList;

public class GestorJugador {

	String nombre;
	String colorFicha;
	int posicion;
	ArrayList<Dado> dados = new ArrayList<Dado>();
	ArrayList<Pez> peces = new ArrayList<Pez>();
	ArrayList<BolaNieve> bolasNieve = new ArrayList<BolaNieve>();

	// CONSTRUCTOR
	public GestorJugador(String nombre, String colorFicha, int posicion, ArrayList<Dado> dados, ArrayList<Pez> peces,
			ArrayList<BolaNieve> bolasNieve) {
		this.nombre = nombre;
		this.colorFicha = colorFicha;
		this.posicion = posicion;
		this.dados = dados;
		this.peces = peces;
		this.bolasNieve = bolasNieve;
	}

	// FUNCIONES
	public void tirarDado() {

	}

	public void usarBolaNieve(ArrayList<GestorJugador> jugadores) {

	}

	public void usarPez() {

	}

	public void avanzar() {

	}

	// GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColorFicha() {
		return colorFicha;
	}

	public void setColorFicha(String colorFicha) {
		this.colorFicha = colorFicha;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}
