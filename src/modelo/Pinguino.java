package modelo;

public class Pinguino {

	Inventario inv;

	// CONSTRUCTOR
	public Pinguino(Inventario inv) {
		this.inv = inv != null ? inv : new Inventario(null); // Lista inicial de items, si es null crea una vacía.
	}

	// GETTERS Y SETTERS
	public Inventario getInv() {
		return inv;
	}

	public void setInv(Inventario inv) {
		this.inv = inv;
	}

}
