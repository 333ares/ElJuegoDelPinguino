package modelo;

import java.util.ArrayList;

public class Inventario {

	ArrayList<Item> lista = new ArrayList<Item>();
	
	// CONSTRUCTOR
	public Inventario(ArrayList<Item> lista) {
		super();
		this.lista = lista;
	}

	
	// GETTERS Y SETTERS
	public ArrayList<Item> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Item> lista) {
		this.lista = lista;
	}
	
	
	public void añadirItem(Item item) {
	    lista.add(item);
	}


	public void quitarItem(Item item) {
	    lista.remove(item);
	}

}
