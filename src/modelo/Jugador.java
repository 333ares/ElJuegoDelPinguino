package modelo;

import java.util.Random;

import controlador.GestorJugador;

public abstract class Jugador extends GestorJugador {

	int posicion;
	String nombre;
	String color;
	Pinguino pinguino;
	Tablero tablero;

	// CONSTRUCTOR
	public Jugador(int posicion, String nombre, String color, Pinguino pinguino) {
		super();
		this.posicion = posicion;
		this.nombre = nombre;
		this.color = color;
		this.pinguino = pinguino;
		this.tablero = tablero;
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

	public Pinguino setPinguino() {
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

	public void tirarDado(int maximoDado) {
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

}
