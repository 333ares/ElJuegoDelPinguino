package modelo;

import java.util.Random;

public class Evento extends Casilla {

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
	    int evento = random.nextInt(4); // Suponiendo 4 tipos de eventos


	    switch (evento) {
	        case 0:
	            System.out.println("¡Has encontrado un pez! +1 pez.");
	            j.getPinguino().getInv().añadirItem(new Item("pez", 1));
	            break;
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
