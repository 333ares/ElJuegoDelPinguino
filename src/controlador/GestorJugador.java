package controlador;

import java.util.Random;
import modelo.Inventario;
import modelo.Jugador;
import modelo.Tablero;

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
		/*
		 * Este método permite que el jugador utilice un ítem específico de su
		 * inventario, identificado por su nombre.
		 * 
		 * Primero, realiza validaciones para asegurarse de que el jugador, su pingüino
		 * y su inventario están correctamente inicializados.
		 * 
		 * Luego, verifica que el inventario contenga el ítem solicitado.
		 * 
		 * Si todo está en orden, ejecuta una estructura switch que identifica el tipo
		 * de ítem usado y aplica su efecto correspondiente: por ejemplo, el “pez”
		 * proporciona protección contra el oso, el “dado rápido” permite avanzar entre
		 * 5 y 10 casillas, y el “dado lento” entre 1 y 3.
		 * 
		 * Cada uso de ítem reduce la cantidad disponible en el inventario.
		 */
		if (jugadorActual == null || jugadorActual.getPinguino() == null
				|| jugadorActual.getPinguino().getInv() == null) {
			System.out.println("Error: Jugador o inventario no inicializado");
			return;
		}

		Inventario inventario = jugadorActual.getPinguino().getInv();

		if (!inventario.contieneItem(nombreItem)) {
			System.out.println("No tienes el item: " + nombreItem);
			return;
		}

		System.out.println("Usando " + nombreItem + "...");

		switch (nombreItem) {
		case "pez":
			if (inventario.getCantidad("pez") > 0) {
				inventario.quitarItem("pez");
				jugadorActual.setProtegidoDelOso(true);
			}
			break;

		case "bola de nieve":
			if (inventario.getCantidad("bola de nieve") > 0) {
				inventario.quitarItem("bola de nieve");
			}
			break;

		case "dado rápido":
			if (inventario.getCantidad("dado rápido") > 0) {
				inventario.quitarItem("dado rápido");
				Random random = new Random();
				int movRapido = random.nextInt(6) + 5; // 5-10
				actualizarMovimientoJugador(jugadorActual, movRapido);
			}
			break;

		case "dado lento":
			if (inventario.getCantidad("dado lento") > 0) {
				inventario.quitarItem("dado lento");
				Random rand = new Random();
				int movLento = rand.nextInt(3) + 1; // 1-3
				actualizarMovimientoJugador(jugadorActual, movLento);
			}
			break;

		default:
			System.out.println("Item no reconocido: " + nombreItem);

		}
	}

	/*
	 * Este método se encarga de mover al jugador en el tablero una cantidad
	 * determinada de casillas.
	 * 
	 * Suma el número de movimientos a la posición actual del jugador y, si la nueva
	 * posición está dentro del rango válido del tablero (entre 0 y 49), actualiza
	 * la posición del jugador.
	 * 
	 * Luego, ejecuta la acción correspondiente a la casilla en la que ha caído,
	 * invocando el método realizarAccion(j) de esa casilla.
	 * 
	 * Si el movimiento resultara en una posición fuera de los límites del tablero,
	 * el método lo detecta y cancela el desplazamiento, mostrando un mensaje de
	 * error.
	 */
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

	/*
	 * Este método se usa para el fin del turno del jugador.
	 * 
	 * Su principal efecto es reiniciar el estado de protección contra el oso, que
	 * puede haber sido activado por el uso de un ítem como el pez.
	 * 
	 * Establece la propiedad protegidoDelOso del jugador como false, indicando que
	 * ya no está protegido.
	 * 
	 * También imprime un mensaje confirmando que el jugador ha finalizado su turno.
	 * 
	 */
	public void jugadorFinalizaTurno() {
		jugadorActual.setProtegidoDelOso(false);
		System.out.println(jugadorActual.getNombre() + " ha finalizado su turno.");
	}
}