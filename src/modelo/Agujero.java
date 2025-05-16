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
            int nuevaPos = gestor.buscarAgujeroAnterior(j.getPosicion());
            System.out.println("Agujero: Retrocediendo a posición " + nuevaPos);
            j.setPosicion(nuevaPos);
        }
    }
}