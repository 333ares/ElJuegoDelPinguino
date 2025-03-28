package Modelo;

import java.util.ArrayList;

public class EventoEspecial {

	String descripción;

	// CONSTRUCTOR
	public EventoEspecial(String descripción) {
		this.descripción = descripción;
	}

	// FUNCIONES
	public void activar(ArrayList<Jugador> jugadores) {

	}

	// GETTERS Y SETTERS
	public String getDescripción() {
		return descripción;
	}

	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}

}
