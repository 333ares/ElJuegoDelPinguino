package controlador;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import modelo.Foca;
import modelo.Item;
import modelo.Jugador;
import modelo.Pinguino;

public class GestorJugador {
/*
 * 
 */
	public void jugadorUsaItem(Jugador j, String nombreItem) {
		//Este método permite al jugador usar un item 
	    for (Item item : j.getPinguino().getInv().getLista()) {// Busca el ítem en el inventario del jugador
	        if (item.getNombre().equals(nombreItem)) {// Implementa el efecto del ítem
	            System.out.println("Usando " + nombreItem + "...");
	            if ("pez".equals(nombreItem)) {// Remueve el ítem del inventario y activa una protección contra el Oso.
	                j.getPinguino().getInv().quitarItem(item);
	                System.out.println("Has usado un pez. Estás protegido del Oso durante este turno.");
	                j.setProtegidoDelOso(true);
	            } else if ("bola de nieve".equals(nombreItem)) {//Permite seleccionar otro jugador para hacerlo retroceder 3 casillas.
	                System.out.println("Has usado una bola de nieve. Selecciona un jugador para retroceder.");
	                // Lógica para seleccionar otro jugador y hacerle retroceder
	                
	                ArrayList<Jugador> otrosJugadores = new ArrayList<>();
	                for (Jugador otroJugador : j.getTablero().getJugadores()) {// Filtra otros jugadores excluyendo al actual (j.getTablero().getJugadores()).
	                    if (!otroJugador.equals(j)) {
	                        otrosJugadores.add(otroJugador);
	                    }
	                }
	                if (!otrosJugadores.isEmpty()) {
	                    System.out.println("Seleccione un jugador:");
	                    for (int i = 0; i < otrosJugadores.size(); i++) {
	                        System.out.println((i + 1) + ". " + otrosJugadores.get(i).getNombre());
	                    }
	                    Scanner s = new Scanner(System.in);
	                    int seleccion = s.nextInt();
	                    if (seleccion > 0 && seleccion <= otrosJugadores.size()) {
	                        Jugador objetivo = otrosJugadores.get(seleccion - 1);
	                        int nuevaPosicion = objetivo.getPosicion() - 3; // Retroceder 3 casillas
	                        if (nuevaPosicion < 0) nuevaPosicion = 0;
	                        objetivo.setPosicion(nuevaPosicion);
	                        System.out.println(objetivo.getNombre() + " retrocede 3 casillas.");
	                    } else {
	                        System.out.println("Selección inválida.");
	                    }
	                } else {
	                    System.out.println("No hay otros jugadores para afectar.");
	                }
	                j.getPinguino().getInv().quitarItem(item);
	            } else if ("dado rápido".equals(nombreItem)) {// Avanza 5-10 casillas.
	                System.out.println("Has usado un dado rápido. Avanzas entre 5-10 casillas.");
	                Random random = new Random();
	                int movimientos = random.nextInt(6) + 5; // 5-10
	                j.moverPosicion(movimientos);
	                j.getPinguino().getInv().quitarItem(item);
	            } else if ("dado lento".equals(nombreItem)) { //Avanza 1-3 casillas.
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

	public void jugadorSeMueve(Jugador j, int movimientos) {// Mueve al jugador en el tablero.
		int nuevaPosicion = j.getPosicion() + movimientos;
		if (nuevaPosicion >= 0 && nuevaPosicion < 50) {// El tablero tiene 50 casillas (posiciones 0 a 49).
			j.setPosicion(nuevaPosicion);
	
			j.getTablero().getCasillas()[nuevaPosicion].realizarAccion(j);
			// Al llegar a una casilla, se ejecuta su acción asociada (realizarAccion(j)).
		} else {
			System.out.println("El movimiento es inválido. El jugador no se moverá.");
		}

	}
	
	public void jugadorFinalizaTurno(Jugador j) {
	    // Resetear la protección contra el Oso
		// Reinicia estados temporales del jugador (como la protección contra el Oso).
	    j.setProtegidoDelOso(false);
	    System.out.println(j.getNombre() + " ha finalizado su turno.");
	}


	public void pinguinoEvento(Pinguino p) {

	}

	public void pinguinoGuerra(Pinguino p) {

	}

	public void focaIntercatua(Pinguino p, Foca f) {

	}
}
