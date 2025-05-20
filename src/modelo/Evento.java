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
		int evento = random.nextInt(4);
		 String mensaje = "";
switch (evento) {
		case 0:
			j.getPinguino().getInv().añadirItem(new Item("pez", 1));
			 mensaje = "Evento: ¡Has obtenido 1 pez!";
			break;
		case 1:
			j.getPinguino().getInv().añadirItem(new Item("bola de nieve", 1));
			 mensaje = "Evento: ¡Has obtenido 1 bola de nieve!";
			break;
		case 2:
			int movRapido = random.nextInt(6) + 5;
			j.setPosicion(j.getPosicion() + movRapido);
			 mensaje = "Evento: ¡Dados rápidos! Avanzas " + movRapido + " casillas";
			break;
		case 3:
			int movLento = random.nextInt(3) + 1;
			j.setPosicion(j.getPosicion() + movLento);
			 mensaje = "Evento: ¡Dados lentos! Avanzas " + movLento + " casillas";
			break;

		}

if (j.getGestorMensajes() != null) {
    j.getGestorMensajes().agregarMensaje(mensaje);
}

	}
}