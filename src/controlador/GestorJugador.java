package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import modelo.Foca;
import modelo.Item;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;
import modelo.Jugador;

public class GestorJugador {
	/*
	 * La clase GestorJugador actúa como un controlador que gestiona las acciones
	 * principales de un jugador en un juego, vinculando las mecánicas del modelo
	 * (como ítems, movimientos y personajes como pingüinos y focas). Sus funciones
	 * clave son:
	 * 
	 * Usar ítems (protección contra un oso, retroceder jugadores rivales, dados
	 * para moverse más rápido o lento),
	 * 
	 * Mover al jugador por un tablero de 50 casillas, activando efectos al caer en
	 * ellas,
	 * 
	 * Finalizar turnos (reiniciando estados temporales como protecciones), y
	 * 
	 * Placeholders para futuras interacciones con eventos, combates o focas (aún no
	 * implementadas). Esta clase funciona como intermediaria entre el jugador, su
	 * inventario, y el tablero, aplicando reglas del juego y efectos de ítems.
	 * 
	 * 
	 */
	private Jugador jugadorActual;
	private Tablero tablero;

	public GestorJugador(Jugador jugador, Tablero tablero) {
		this.tablero = tablero;
		this.jugadorActual = jugador;
	}


	 public void jugadorUsaItem(String nombreItem) {
	       // Este método permite al jugador usar un item
	       for (Item item : jugadorActual.getPinguino().getInv().getLista()) { // Busca el ítem en el inventario del jugador
	           if (item.getNombre().equals(nombreItem)) { // Implementa el efecto del ítem
	                System.out.println("Usando " + nombreItem + "...");
	                if ("pez".equals(nombreItem)) { // Remueve el ítem del inventario y activa una protección contra el Oso.
	                    jugadorActual.getPinguino().getInv().quitarItem(item);
	                    System.out.println("Has usado un pez. Estás protegido del Oso durante este turno.");
	                    jugadorActual.setProtegidoDelOso(true);
	                } else if ("bola de nieve".equals(nombreItem)) { // Permite seleccionar otro jugador para hacerlo retroceder 3 casillas.
	                    System.out.println("Has usado una bola de nieve. Selecciona un jugador para retroceder.");
	                    // Lógica para seleccionar otro jugador y hacerle retroceder
	                    // (Implementar lógica si hay más de un jugador, en este caso no es necesario)
	                } else if ("dado rápido".equals(nombreItem)) { // Avanza 5-10 casillas.
	                    System.out.println("Has usado un dado rápido. Avanzas entre 5-10 casillas.");
	                    Random random = new Random();
	                    int movimientos = random.nextInt(6) + 5; // 5-10
	                    actualizarMovimientoJugador(jugadorActual, movimientos);
	                    jugadorActual.getPinguino().getInv().quitarItem(item);
	                } else if ("dado lento".equals(nombreItem)) { // Avanza 1-3 casillas.
	                    System.out.println("Has usado un dado lento. Avanzas entre 1-3 casillas.");
	                    Random random = new Random();
	                    int movimientos = random.nextInt(3) + 1; // 1-3
	                    actualizarMovimientoJugador(jugadorActual, movimientos);
	                    jugadorActual.getPinguino().getInv().quitarItem(item);
	                }
	                break;
	            }
	        }

	    }

	    public void actualizarMovimientoJugador(Jugador j, int movimientos) { // Mueve al jugador en el tablero.
	        int nuevaPosicion = j.getPosicion() + movimientos;
	        if (nuevaPosicion >= 0 && nuevaPosicion < 50) { // El tablero tiene 50 casillas (posiciones 0 a 49).
	            j.setPosicion(nuevaPosicion);

	            tablero.getCasillas()[nuevaPosicion].realizarAccion(j);
	            // Al llegar a una casilla, se ejecuta su acción asociada (realizarAccion(j)).
	        } else {
	            System.out.println("El movimiento es inválido. El jugador no se moverá.");
	        }
	    }

	    public void jugadorFinalizaTurno() {
	        jugadorActual.setProtegidoDelOso(false);
	        System.out.println(jugadorActual.getNombre() + " ha finalizado su turno.");
	    }

	    public void cambiarJugadorActual() {
	        // No es necesario si solo hay un jugador
	    }

	    public void pinguinoEvento(Pinguino p) {

	    }

	    public void pinguinoGuerra(Pinguino p) {

	    }

	    public void focaInteractua(Pinguino p, Foca f) {

	    }
	}