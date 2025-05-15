package modelo;

public class Pinguino {

	Inventario inv;

	// CONSTRUCTOR
	public Pinguino(Inventario inv) {
		this.inv = inv != null ? inv : new Inventario(null);
	}

	// GETTERS Y SETTERS
	public Inventario getInv() {
		return inv;
	}

	public void setInv(Inventario inv) {
		this.inv = inv;
	}

	// FUNCIONES
	public void gestionarBatala() {

	}

	public void usarObjeto() {

	}

	public void añadirItem() {

	}

	public void quitarItem() {

	}

}
