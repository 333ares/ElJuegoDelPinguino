package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controlador.GestorTablero;

public class Tablero {

	/*
	 * Hereda de Casilla y representa un obstáculo en el tablero.
	 * 
	 * Constructor solo necesita posición (delega a clase padre).
	 * 
	 * Al activarse (realizarAccion):
	 * 
	 * Notifica: "El oso atrapó al pingüino"
	 * 
	 * Penaliza: envía jugador al inicio (posición 0)
	 * 
	 * Ejemplo de efecto negativo en casillas especiales
	 * 
	 * Usa herencia para personalizar comportamiento base de Casilla
	 * 
	 * 
	 */
	private Casilla[] casillas;
	private Jugador jugadorActual;
	private int turnos;

	// Registros de posiciones especiales
	private List<Integer> posicionesAgujeros = new ArrayList<>();
	private List<Integer> posicionesOsos = new ArrayList<>();
	private List<Integer> posicionesTrineos = new ArrayList<>();
	private List<Integer> posicionesEventos = new ArrayList<>();

	public Tablero() {
		this.casillas = new Casilla[50];
		this.jugadorActual = inicializarJugador();
		this.turnos = 0;
		inicializarCasillas();
	}

	public void inicializarCasillas() {
		for (int i = 0; i < casillas.length; i++) {
			int tipo = (int) (Math.random() * 10);
			switch (tipo) {
			case 0:
				casillas[i] = new Oso(i);
				posicionesOsos.add(i);
				break;
			case 1:
				casillas[i] = new Agujero(i, null);
				posicionesAgujeros.add(i);
				break;
			case 2:
				casillas[i] = new Trineo(i);
				posicionesTrineos.add(i);
				break;
			case 3:
				casillas[i] = new Evento(i);
				posicionesEventos.add(i);
				break;
			default:
				casillas[i] = new CasillaNormal(i);
				break;
			}
		}
	}

	private Jugador inicializarJugador() {
		Inventario inv = new Inventario(new ArrayList<>());
		Pinguino pinguino = new Pinguino(inv);
		return new Jugador(0, "Jugador 1", "Azul", pinguino);
	}

	public void avanzarTurno() {
		turnos++;
	}

	public void actualizarTablero() {

	}

	public void actualizarjugador(Jugador j) {
		jugadorActual = j;
	}

	// Métodos para acceder a posiciones especiales

	public List<Integer> getPosicionesAgujeros() {
		return Collections.unmodifiableList(posicionesAgujeros);
	}

	public List<Integer> getPosicionesOsos() {
		return Collections.unmodifiableList(posicionesOsos);
	}

	public List<Integer> getPosicionesTrineos() {
		return posicionesTrineos;
	}

	public List<Integer> getPosicionesEventos() {
		return posicionesEventos;
	}

	// getters y setters
	public Casilla[] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[] casillas) {
		this.casillas = casillas;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setjugadorActual (Jugador jugadorActual) {
		this.jugadorActual =jugadorActual;
	}

	public int getTurnos() {
		return turnos;
	}

	public void setTurnos(int turnos) {
		this.turnos = turnos;
	}

	public void setPosicionesTrineos(List<Integer> posicionesTrineos) {
		this.posicionesTrineos = posicionesTrineos;
	}
	public void setPosicionesEventos(List<Integer> posicionesEventos) {
		this.posicionesEventos = posicionesEventos;
	}
	public void setPosicionesAgujeros(List<Integer> posicionesAgujeros) {
		this.posicionesAgujeros = posicionesAgujeros;
	}
	public void setPosicionesOsos(List<Integer> posicionesOsos) {
		this.posicionesOsos = posicionesOsos;
	}

}