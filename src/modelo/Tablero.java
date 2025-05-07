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
		this.jugadorActual = null ;
		this.jugadores = new ArrayList<Jugador>();
		inicializarCasillas();
		initializarJugadores();
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
	
	public void inicializarCasillas() {
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
		//Este método imprime en consola el tipo de cada casilla del tablero para verificar que se hayan creado correctamente.
		  for (int i = 0; i < casillas.length; i++) {
		        System.out.print(casillas[i].getClass().getSimpleName() + " ");
		        // casillas[i] obtendra el nombre de la clase en la posicion i (Por ejemplo: Oso).
		        if ((i + 1) % 10 == 0) 
		        System.out.println();// Salto de línea cada 10 casillas
		        // Este condicional verifica si la posición actual (i + 1) es divisible por 10.
		        // Si es así, significa que hemos llegado al final de una fila.
		        //  Y se imprimira un salto de línea para que la siguiente casilla aparezca en una nueva fila.

		        
		    }
	}
	
    private void initializarJugadores() {
    	 Jugador jugador1 = new Jugador(turnos, "Jugador 1", null, null);
    	    Jugador jugador2 = new Jugador(turnos, "Jugador 2", null, null);

    	    // Agregar jugadores al tablero
    	    jugadores.add(jugador1);
    	    jugadores.add(jugador2);
    	    this.jugadorActual = jugador1; // Establecer el primer jugador como jugador actual
    }
}
