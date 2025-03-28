package Modelo;

import java.util.ArrayList;

public class EventoEspecial {

	String descripcion;

	// CONSTRUCTOR
	public EventoEspecial(String descripción) {
		this.descripcion = descripción;
	}

	// FUNCIONES
	public void activar(ArrayList<Jugador> jugadores) {

	}

	// GETTERS Y SETTERS
	public String getDescripción() {
		return descripcion;
	}

	public void setDescripción(String descripción) {
		this.descripcion = descripción;
	}

}
