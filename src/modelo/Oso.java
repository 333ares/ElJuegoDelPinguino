package modelo;

public class Oso extends Casilla {

	public Oso(int posicion) {
		super(posicion);
	}

	public void realizarAccion(Jugador j) {
		//Acción que realizara el oso.
		System.out.println("El oso ha atrapado al pingüino. Vuelves al inicio.");
	        j.setPosicion(0); // Retorna al inicio
	        
	}
	
	
}