package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import controlador.GestorJugador;
import controlador.GestorTablero;

public class Tablero {

	/*
	 * Representa el tablero de juego con sus casillas especiales y estado del juego.
	 * Gera la lógica principal del tablero y la interacción con los jugadores.
	 */
	private Casilla[] casillas;
	private Jugador jugadorActual;
	private int turnos;
	private GestorTablero gestorTablero;
	private GestorJugador gestorJugador;

	// Listas de posiciones de casillas especiales
	private List<Integer> posicionesAgujeros = new ArrayList<>();
	private List<Integer> posicionesOsos = new ArrayList<>();
	private List<Integer> posicionesTrineos = new ArrayList<>();
	private List<Integer> posicionesEventos = new ArrayList<>();

	public void setGestorTablero(GestorTablero gestorTablero) {
		this.gestorTablero = gestorTablero;
		// Pasamos las posiciones de agujeros al gestor
		// this.gestorTablero.setPosicionesAgujeros(new ArrayList<>(this.posicionesAgujeros));
	}

	public void setGestorJugador(GestorJugador gestorJugador) {
		this.gestorJugador = gestorJugador;
	}

	public GestorJugador getGestorJugador() {
		return this.gestorJugador;
	}

	/*
     * Constructor que inicializa un tablero con:
     * - 50 casillas
     * - Un jugador inicial
     * - Turno en 0
     * - Casillas especiales distribuidas aleatoriamente
     */
	public Tablero() {
		this.casillas = new Casilla[50];
		this.jugadorActual = inicializarJugador();
		this.turnos = 0;
		inicializarCasillas();
	}

	public void inicializarTableroCompleto() {
	    inicializarCasillas();
	}

	public void inicializarCasillas() {
		// Limpieza de listas anteriores
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
			case 1:
			case 2: // Agujero (10% probabilidad)
				crearCasillaEspecial(i, new Agujero(i, gestorTablero), posicionesAgujeros);
				break;
			case 3:
			case 4: // Trineo (10% probabilidad)
				crearCasillaEspecial(i, new Trineo(i), posicionesTrineos);
				break;
			case 5:
			case 6: // Evento (10% probabilidad)
				crearCasillaEspecial(i, new Evento(i), posicionesEventos);
				break;
			default: // Casilla normal (65% probabilidad)
				casillas[i] = new CasillaNormal(i);
			}
		}

		asegurarCasillasEspeciales();
	}

	// Crea una casilla especial y registra su posición
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
		if (casillas[posicion] instanceof Oso) { // instanceof = operador de Java que comprueba si un objeto es una
			// instancia de una clase específica o de sus subclases
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
	
	public String serializar() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(turnos).append(";");
	    
	    // Serializar casillas especiales
	    for (int i = 0; i < casillas.length; i++) {
	        String tipo = getCasillaTipo(i);
	        if (!"Normal".equals(tipo)) {
	            sb.append(i).append(":").append(tipo).append(",");
	        }
	    }
	    
	    return sb.toString();
	}

	public void deserializar(String estado) {
	    String[] partes = estado.split(";");
	    this.turnos = Integer.parseInt(partes[0]);
	    
	    // Reconstruir casillas especiales
	    if (partes.length > 1) {
	        String[] casillasEspeciales = partes[1].split(",");
	        for (String casilla : casillasEspeciales) {
	            if (!casilla.isEmpty()) {
	                String[] datos = casilla.split(":");
	                int posicion = Integer.parseInt(datos[0]);
	                String tipo = datos[1];
	                
	                switch (tipo) {
	                    case "Oso":
	                        crearCasillaEspecial(posicion, new Oso(posicion), posicionesOsos);
	                        break;
	                    case "Agujero":
	                        crearCasillaEspecial(posicion, new Agujero(posicion, gestorTablero), posicionesAgujeros);
	                        break;
	                    // ... otros tipos
	                }
	            }
	        }
	    }
	}

}