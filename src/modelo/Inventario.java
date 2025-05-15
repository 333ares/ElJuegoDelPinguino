package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventario {
	 
	ArrayList<Item> lista = new ArrayList<Item>();
	  private Map<String, Integer> items;

	/*
	 * Almacena ítems en un ArrayList. Permite añadir/eliminar ítems (por objeto o
	 * nombre), verificar su existencia y gestionar cantidades. Usa getters/setters
	 * para acceso a la lista. La eliminación por nombre reduce la cantidad o borra
	 * el ítem si es 1. Falta implementar getCantidad() completamente.
	 * 
	 */

	// CONSTRUCTOR
	public Inventario(ArrayList<Item> lista) {
		super();
		this.lista = lista;
		this.items = new HashMap<>();
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

	public boolean contieneItem(String nombre) {// Método que verifica si el inventario contiene un ítem con cierto
												// nombre.
		for (Item item : lista) {
			if (item.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Busca el ítem por nombre en la lista.
	 * 
	 * Si la cantidad del ítem es mayor que 1, reduce la cantidad en 1.
	 * 
	 * Si la cantidad es 1, elimina completamente el ítem de la lista.
	 * 
	 * break: Sale del bucle una vez que encuentra y procesa el ítem.
	 */
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

	public int getCantidad(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void añadirItem(String nombre, int cantidad) {
		// TODO Auto-generated method stub
		 items.put(nombre, items.getOrDefault(nombre, 0) + cantidad);
	}

}