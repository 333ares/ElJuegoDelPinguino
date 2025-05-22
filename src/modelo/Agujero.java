package modelo;

import java.util.ArrayList;
import java.util.List;

import controlador.GestorTablero;

public class Agujero extends Casilla {
	private GestorTablero gestor;

	public Agujero(int posicion, GestorTablero gestor) {
		super(posicion);
		this.gestor = gestor;
	}

	public void realizarAccion(Jugador j) {
		if (gestor != null) {
			// Obtener las posiciones actuales de agujeros directamente del tablero
			List<Integer> posicionesActuales = gestor.getTablero().getPosicionesAgujeros();
			gestor.setPosicionesAgujeros(new ArrayList<>(posicionesActuales));

			int nuevaPos = gestor.buscarAgujeroAnterior(getPosicion());
			j.setPosicion(nuevaPos);

			String mensaje = "Has caído en un Agujero: Retrocedes a la casilla " + nuevaPos;
			if (j.getGestorMensajes() != null) {
				j.getGestorMensajes().agregarMensaje(mensaje);
			}

			// Evitar bucle infinito: solo activar acción si la casilla destino no es un
			// agujero
			Casilla destino = gestor.getTablero().getCasillas()[nuevaPos];
			if (!(destino instanceof Agujero)) { // Añadido esta condición
				destino.realizarAccion(j);
			}
		}

	}
}