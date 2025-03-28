package Modelo;

import java.util.ArrayList;

public class Partida {

	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	ArrayList<CPU> cpu = new ArrayList<CPU>();
	int[][] tablero;
	int turnoActual;

	// CONSTRUCTOR
	public Partida(ArrayList<Jugador> jugadores, ArrayList<CPU> cpu, int[][] tablero, int turnoActual) {
		super();
		this.jugadores = jugadores;
		this.cpu = cpu;
		this.tablero = tablero;
		this.turnoActual = turnoActual;
	}

	// FUNCIONES
	public void guardarPartida() {

	}

	public void cargarPartida() {

	}

	public void siguienteTurno() {

	}

	// GETTERS Y SETTERS
	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public int getTurnoActual() {
		return turnoActual;
	}

	public void setTurnoActual(int turnoActual) {
		this.turnoActual = turnoActual;
	}

}
