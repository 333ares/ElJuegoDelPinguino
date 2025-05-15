package modelo;

import java.util.Random;

import controlador.GestorJugador;

public class Jugador {

	int posicion;
	String nombre;
	String color;
	Pinguino pinguino;
	Tablero tablero;

	/*
	 * Clase que representa un jugador con posición, nombre, color y un Pinguino
	 * asociado. Contiene métodos para moverse por el tablero (0-49 posiciones) y
	 * lanzar dados (1-6). Gestiona protección contra osos (protegidoDelOso) y usa
	 * getters/setters para acceder a sus atributos. El constructor inicializa los
	 * datos básicos y asocia un tablero (aunque la asignación actual tiene un bug).
	 * 
	 */

	// CONSTRUCTOR
	public Jugador(int posicion, String nombre, String color, Pinguino pinguino) {
		super();
		this.posicion = posicion;
		this.nombre = nombre;
		this.color = color;
		this.pinguino = pinguino != null ? pinguino : new Pinguino(new Inventario());
		this.tablero = tablero;
		
		   // Inicializar con algunos items básicos
        if (this.pinguino.getInv() != null) {
            this.pinguino.getInv().añadirItem("dado rápido", 1);
            this.pinguino.getInv().añadirItem("dado lento", 1);
            this.pinguino.getInv().añadirItem("bola de nieve", 2);
            this.pinguino.getInv().añadirItem("pez", 3);
        }
	}

	// GETTERS Y SETTERS

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Pinguino getPinguino() { // El método getPinguino se utiliza para obtener el objeto Pinguino asociado al
									// jugador.
		return pinguino;
	}

	public Pinguino setPinguino(Pinguino pinguino) {
		return pinguino;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// FUNCIONES

	public void tirarDado() {
		Random random = new Random();
		int resultado = random.nextInt(6) + 1; // Dado de 1 a 6
		System.out.println("Has tirado el dado y ha salido: " + resultado);

	}

	public void moverPosicion(int nuevaPosicion) {
		// El if se utiliza para asegurar que la nueva posición del jugador esté dentro
		// de los límites del tablero (0 a 49, ya que hay 50 casillas).
		if (nuevaPosicion >= 0 && nuevaPosicion < 50) {
			this.posicion = nuevaPosicion;
		}

	}

	// Método para proteger al jugador del Oso
	private boolean protegidoDelOso = false;
	// Controla si el jugador está protegido contra osos.

	public void setProtegidoDelOso(boolean protegidoDelOso) {
		this.protegidoDelOso = protegidoDelOso;
	}

	public boolean isProtegidoDelOso() {
		return protegidoDelOso;
	}

}
