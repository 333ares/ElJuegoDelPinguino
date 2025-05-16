package modelo;

import controlador.GestorTablero;

public class Agujero extends Casilla {
    private GestorTablero gestor;

    public Agujero(int posicion, GestorTablero gestor) {
        super(posicion);
        this.gestor = gestor;
    }

    public void realizarAccion(Jugador j) {
    	  int nuevaPos = gestor.buscarAgujeroAnterior(getPosicion());
          System.out.println("¡Caíste en un agujero! Retrocedes a " + nuevaPos);
          j.setPosicion(nuevaPos);

    }
}