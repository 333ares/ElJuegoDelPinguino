package modelo;

import java.util.Random;

public class Evento extends Casilla {

	/*
	 * La clase representa una casilla especial de evento en un juego de tablero.
	 * Cuando un jugador cae en ella, se ejecuta una acción aleatoria que puede:
	 * 
	 * Darle 1 pez al inventario del jugador.
	 * 
	 * Darle 1 bola de nieve al inventario.
	 * 
	 * Hacer que el jugador avance entre 5 y 10 casillas (dados rápidos).
	 * 
	 * Hacer que avance entre 1 y 3 casillas (dados lentos).
	 * 
	 * Esto se hace con ayuda de la clase Random, usando un switch para seleccionar
	 * una de las 4 acciones posibles.
	 */
	String tipoEvento;

	// CONSTRUCTOR
	public Evento(int posicion) {
		super(posicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	// Define el comportamiento que ocurre cuando un jugador cae en una casilla de
	// evento.
	public void realizarAccion(Jugador j) {
		Random random = new Random();
		int evento = random.nextInt(4); // SGenera un número aleatorio entre 0 y 3 (4 eventos posibles). El número
										// determinará qué tipo de evento ocurre.

		switch (evento) {
		case 0:
			System.out.println("¡Has encontrado un pez! +1 pez.");
			j.getPinguino().getInv().añadirItem(new Item("pez", 1));
			break;
		// Se accede al inventario del pingüino del jugador y se añade un objeto tipo
		// Item con nombre "pez" y cantidad 1
		case 1:
			System.out.println("¡Has encontrado una bola de nieve! +1 bola de nieve.");
			j.getPinguino().getInv().añadirItem(new Item("bola de nieve", 1));
			break;
		case 2:
			System.out.println("¡Dados rápidos! Avanza entre 5-10 casillas.");
			int movimientosRapidos = random.nextInt(6) + 5; // 5-10
			j.moverPosicion(movimientosRapidos);
			break;
		case 3:
			System.out.println("¡Dados lentos! Avanza entre 1-3 casillas.");
			int movimientosLentos = random.nextInt(3) + 1; // 1-3
			j.moverPosicion(movimientosLentos);
			break;
		}
	}
}
