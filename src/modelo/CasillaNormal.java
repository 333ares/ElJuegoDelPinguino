package modelo;

public class CasillaNormal extends Casilla {

    public CasillaNormal(int posicion) {
        super(posicion);
    }
    
    public void realizarAccion(Jugador j) {
    	 System.out.println("Casilla normal. Continúa tu turno.");
    }

}