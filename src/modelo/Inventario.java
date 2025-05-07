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

	public boolean contieneItem(String nombre) {
	    for (Item item : lista) {
	        if (item.getNombre().equals(nombre)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void quitarItem(String nombre) {
	    for (int i = 0; i < lista.size(); i++) {
	        Item item = lista.get(i);
	        if (item.getNombre().equals(nombre)) {
	            if (item.getCantidad() > 1) {
	                item.setCantidad(item.getCantidad() - 1);
	            } else {
	                lista.remove(i);
	            }
	            break;
	        }
	    }
	}
	
	
}
