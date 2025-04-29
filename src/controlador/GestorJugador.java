package controlador;

import java.util.Random;

import modelo.Foca;
import modelo.Item;
import modelo.Jugador;
import modelo.Pinguino;

public class GestorJugador {

	public void jugadorUsaItem(Jugador j, String nombreItem) {
		  // Busca el ítem en el inventario del jugador
	    for (Item item : j.getPinguino().getInv().getLista()) {
	        if (item.getNombre().equals(nombreItem)) {
	            // Implementa el efecto del ítem
	            System.out.println("Usando " + nombreItem + "...");
	            if ("pez".equals(nombreItem)) {
	                j.getPinguino().getInv().quitarItem(item);
	                System.out.println("Has usado un pez. Estás protegido del Oso durante este turno.");
	                j.setProtegidoDelOso(true);
	            } else if ("bola de nieve".equals(nombreItem)) {
	                System.out.println("Has usado una bola de nieve. Selecciona un jugador para retroceder.");
	                // Ejemplo de lógica para seleccionar otro jugador y hacerle retroceder
	                for (Jugador otroJugador : j.getTablero().getJugadores()) {
	                    if (!otroJugador.equals(j)) {
	                        int nuevaPosicion = otroJugador.getPosicion() - 3; // Retroceder 3 casillas
	                        if (nuevaPosicion < 0) nuevaPosicion = 0;
	                        otroJugador.setPosicion(nuevaPosicion);
	                        System.out.println(otroJugador.getNombre() + " retrocede 3 casillas.");
	                        break; // Solo afecta al primer jugador encontrado
	                    }
	                }
	                j.getPinguino().getInv().quitarItem(item);
	            } else if ("dado rápido".equals(nombreItem)) {
	                System.out.println("Has usado un dado rápido. Avanzas entre 5-10 casillas.");
	                Random random = new Random();
	                int movimientos = random.nextInt(6) + 5; // 5-10
	                j.moverPosicion(movimientos);
	                j.getPinguino().getInv().quitarItem(item);
	            } else if ("dado lento".equals(nombreItem)) {
	                System.out.println("Has usado un dado lento. Avanzas entre 1-3 casillas.");
	                Random random = new Random();
	                int movimientos = random.nextInt(3) + 1; // 1-3
	                j.moverPosicion(movimientos);
	                j.getPinguino().getInv().quitarItem(item);
	            }
	            break;
	        }
	    }

	}

	public void jugadorSeMueve(Jugador j, int movimientos) {
		// Define la lógica para mover un jugador por el tablero.
		int nuevaPosicion = j.getPosicion() + movimientos;
		if (nuevaPosicion >= 0 && nuevaPosicion < 50) {
			j.setPosicion(nuevaPosicion);
			// Activa el efecto de la casilla en la nueva posición
			j.getTablero().getCasillas()[nuevaPosicion].realizarAccion(j);
		} else {
			System.out.println("El movimiento es inválido. El jugador no se moverá.");
		}

	}

	public void jugadorFinalizaTurno(Jugador j) {

	}

	public void pinguinoEvento(Pinguino p) {

	}

	public void pinguinoGuerra(Pinguino p) {

	}

	public void focaIntercatua(Pinguino p, Foca f) {

	}
}
