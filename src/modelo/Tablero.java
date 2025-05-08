package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controlador.GestorTablero;

public class Tablero {
    private Casilla[] casillas;
    private ArrayList<Jugador> jugadores;
    private int turnos;
    private Jugador jugadorActual;
    
    // Registros de posiciones especiales
    private List<Integer> posicionesAgujeros = new ArrayList<>();
    private List<Integer> posicionesOsos = new ArrayList<>();
    private List<Integer> posicionesTrineos = new ArrayList<>();
    private List<Integer> posicionesEventos = new ArrayList<>();

    public Tablero() {
        this.casillas = new Casilla[50];
        this.jugadores = new ArrayList<>();
        this.turnos = 0;
        inicializarCasillas();
        inicializarJugadores();
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
                    casillas[i] = new Agujero(i, null); // public Agujero(int posicion, GestorTablero gestor)
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

    private void inicializarJugadores() {
        Inventario inv1 = new Inventario(new ArrayList<>());
        Inventario inv2 = new Inventario(new ArrayList<>());
        
        Pinguino pinguino1 = new Pinguino(inv1);
        Pinguino pinguino2 = new Pinguino(inv2);
        
        Jugador jugador1 = new Jugador(0, "Jugador 1", "Azul", pinguino1);
        Jugador jugador2 = new Jugador(0, "Jugador 2", "Rojo", pinguino2);
        
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        this.jugadorActual = jugador1;
    }

    // getters y setters 

    public void avanzarTurno() {
        turnos++;
        cambiarJugadorActual();
    }

    private void cambiarJugadorActual() {
        int indiceActual = jugadores.indexOf(jugadorActual);
        int siguienteIndice = (indiceActual + 1) % jugadores.size();
        jugadorActual = jugadores.get(siguienteIndice);
    }

    public void mostrarTablero() {
        System.out.println("=== ESTADO DEL TABLERO ===");
        for (int i = 0; i < casillas.length; i++) {
            String simbolo;
            if (casillas[i] instanceof Oso) simbolo = "[O]";
            else if (casillas[i] instanceof Agujero) simbolo = "[A]";
            else if (casillas[i] instanceof Trineo) simbolo = "[T]";
            else if (casillas[i] instanceof Evento) simbolo = "[?]";
            else simbolo = "[ ]";
            
            // Mostrar jugadores en la casilla
            for (Jugador j : jugadores) {
                if (j.getPosicion() == i) {
                    simbolo = simbolo.replace("]", j.getNombre().charAt(0) + "]");
                }
            }
            
            System.out.print(simbolo + " ");
            if ((i + 1) % 10 == 0) System.out.println();
        }
    }
    
	public void actualizarTablero() {
		
	}

	public void actualizarjugador(Jugador j) {

	}

    // Métodos para acceder a posiciones especiales
    
    public List<Integer> getPosicionesAgujeros() {
        return Collections.unmodifiableList(posicionesAgujeros);
    }
    
    public List<Integer> getPosicionesOsos() {
        return Collections.unmodifiableList(posicionesOsos);
    }

    // getters y setters 
	public Casilla[] getCasillas() {
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

	public List<Integer> getPosicionesTrineos() {
		return posicionesTrineos;
	}

	public void setPosicionesTrineos(List<Integer> posicionesTrineos) {
		this.posicionesTrineos = posicionesTrineos;
	}

	public List<Integer> getPosicionesEventos() {
		return posicionesEventos;
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