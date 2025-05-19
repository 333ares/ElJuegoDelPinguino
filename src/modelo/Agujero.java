package modelo;

import controlador.GestorTablero;

public class Agujero extends Casilla {
	private GestorTablero gestor;

	public Agujero(int posicion, GestorTablero gestor) {
		super(posicion);
		this.gestor = gestor;
	}

	public void realizarAccion(Jugador j) {
		// Buscar posición del agujero anterior
		int nuevaPos = gestor.buscarAgujeroAnterior(getPosicion());

		// Mover jugador a la nueva posición
		j.setPosicion(nuevaPos);

		// Verificar si la nueva posición tiene otro efecto
		Casilla destino = gestor.getTablero().getCasillas()[nuevaPos];

		// Si la nueva casilla NO es normal, activar su efecto
		if (!(destino instanceof CasillaNormal)) { // instanceof = operador de Java que comprueba si un objeto es una
													// instancia de una clase específica o de sus subclases
			destino.realizarAccion(j);
		}
	}
}