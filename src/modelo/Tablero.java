package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	private GestorTablero gestorTablero;

	// Registros de posiciones especiales
	private List<Integer> posicionesAgujeros = new ArrayList<>();
	private List<Integer> posicionesOsos = new ArrayList<>();
	private List<Integer> posicionesTrineos = new ArrayList<>();
	private List<Integer> posicionesEventos = new ArrayList<>();

	public void setGestorTablero(GestorTablero gestorTablero) {
	    this.gestorTablero = gestorTablero;
	    // Pasamos las posiciones de agujeros al gestor
	    this.gestorTablero.setPosicionesAgujeros(new ArrayList<>(this.posicionesAgujeros));
	}


	public Tablero() {
		this.casillas = new Casilla[50];
		this.jugadorActual = inicializarJugador();
		this.turnos = 0;
		inicializarCasillas();
	}

	public void inicializarCasillas() {
		// Limpiar listas de posiciones especiales
	    posicionesOsos.clear();
	    posicionesAgujeros.clear();
	    posicionesTrineos.clear();
	    posicionesEventos.clear();

	    Random rand = new Random();
	    
	    for (int i = 0; i < casillas.length; i++) {
	        int tipo = rand.nextInt(20); // 0-19
	        
	        switch (tipo) {
	            case 0: // Oso (5% probabilidad)
	                crearCasillaEspecial(i, new Oso(i), posicionesOsos);
	                break;
	            case 1: case 2: // Agujero (10% probabilidad)
	                crearCasillaEspecial(i, new Agujero(i, gestorTablero), posicionesAgujeros);
	                break;
	            case 3: case 4: // Trineo (10% probabilidad)
	                crearCasillaEspecial(i, new Trineo(i), posicionesTrineos);
	                break;
	            case 5: case 6: // Evento (10% probabilidad)
	                crearCasillaEspecial(i, new Evento(i), posicionesEventos);
	                break;
	            default: // Casilla normal (65% probabilidad)
	                casillas[i] = new CasillaNormal(i);
	        }
	    }

	    asegurarCasillasEspeciales();
	}

	private void crearCasillaEspecial(int posicion, Casilla casilla, List<Integer> listaPosiciones) {
	    casillas[posicion] = casilla;
	    listaPosiciones.add(posicion);
	}

	private void asegurarCasillasEspeciales() {
	    // Asegurar al menos 1 oso
	    if (posicionesOsos.isEmpty()) {
	        int pos = new Random().nextInt(casillas.length);
	        crearCasillaEspecial(pos, new Oso(pos), posicionesOsos);
	    }
	}

	// Método que devuelve el tipo de casilla en una posición específica
	public String getCasillaTipo(int posicion) {
		if (posicion < 0 || posicion >= casillas.length) {
			return "Invalid";
		}
		if (casillas[posicion] instanceof Oso) {
			return "Oso";
		} else if (casillas[posicion] instanceof Agujero) {
			return "Agujero";
		} else if (casillas[posicion] instanceof Trineo) {
			return "Trineo";
		} else if (casillas[posicion] instanceof Evento) {
			return "Evento";
		} else {
			return "Normal";
		}
	}

	public Jugador inicializarJugador() {
		Inventario inv = new Inventario(new ArrayList<>());
		Pinguino pinguino = new Pinguino(inv);
		return new Jugador(0, "Jugador 1", "Azul", pinguino);
	}

	public void avanzarTurno() {
		turnos++;
	}

	public void actualizarTablero() {

	}

	public void actualizarJugador(Jugador j) {
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

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
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