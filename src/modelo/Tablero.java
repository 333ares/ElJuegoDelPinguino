package modelo;

import java.util.ArrayList;

public class Tablero {

	static Casilla [] casillas;
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	int turnos;
	Jugador jugadorActual;

	// CONSTRUCTOR
	public Tablero(Casilla[] casillas, ArrayList<Jugador> jugadores, int turnos, Jugador jugadorActual) {
		super();
		this.casillas = new Casilla[50];
		this.jugadores = jugadores;
		this.turnos = turnos;
		this.jugadorActual = jugadorActual;
		inicializarCasillas();
	}

	// GETTERS Y SETTERS
	public static Casilla[] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[] casillas) {
		this.casillas = casillas;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getTurnos() {
		return turnos;
	}

	public void setTurnos(int turnos) {
		this.turnos = turnos;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

	// FUNCIONES
	public void actualizarTablero() {
		
	}

	public void actualizarjugador(Jugador j) {

	}
	
	private void inicializarCasillas() {
	    for (int i = 0; i < casillas.length; i++) {
	        // Aumentamos el número de opciones para que haya más casillas normales
	        int tipo = (int) (Math.random() * 10); // 10 opciones
	        switch (tipo) {
	            case 0:
	                casillas[i] = new Oso(i);
	                break;
	            case 1:
	                casillas[i] = new Agujero(i);
	                break;
	            case 2:
	                casillas[i] = new Trineo(i);
	                break;
	            case 3:
	                casillas[i] = new Evento(i);
	                break;
	            default: // Casilla normal para los casos 4-9
	                casillas[i] = new CasillaNormal(i);
	                break;
	        }
	    }
	}
	
	public void mostrarTablero () {
		  for (int i = 0; i < casillas.length; i++) {
		        System.out.print(casillas[i].getClass().getSimpleName() + " ");
		        if ((i + 1) % 10 == 0) System.out.println();
		    }
	}
}
