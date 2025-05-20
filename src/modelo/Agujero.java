package modelo;

import controlador.GestorTablero;

public class Agujero extends Casilla {
	private GestorTablero gestor;

	public Agujero(int posicion, GestorTablero gestor) {
		super(posicion);
		this.gestor = gestor;
	}

	public void realizarAccion(Jugador j) {
		if (gestor != null) {
            // Buscar posición del agujero anterior
            int nuevaPos = gestor.buscarAgujeroAnterior(getPosicion());
            
            // Mover jugador a la nueva posición
            j.setPosicion(nuevaPos);
            
            String mensaje = "Has caído en un Agujero: Retrocedes a la casilla " + nuevaPos;
            if (j.getGestorMensajes() != null) {
                j.getGestorMensajes().agregarMensaje(mensaje);
            }

            // Verificar si la nueva posición tiene otro efecto
            Casilla destino = gestor.getTablero().getCasillas()[nuevaPos];
            
            // Si la nueva casilla NO es normal, activar su efecto
            if (!(destino instanceof CasillaNormal)) {
                destino.realizarAccion(j);
            }
        }

	}
}