package modelo;

import java.util.ArrayList;

public class Inventario {
	private ArrayList<Item> items;

	public Inventario(ArrayList<Item> items) {
		this.items = items != null ? items : new ArrayList<>(); // Lista inicial de items, si es null crea una vacía.
	}

	// Añade un ítem al inventario
	public void añadirItem(Item item) {
		// Busca si ya existe un ítem con el mismo nombre
		for (Item i : items) {
			if (i.getNombre().equals(item.getNombre())) {
				// Si existe, incrementa la cantidad
				i.setCantidad(i.getCantidad() + item.getCantidad());
				return;
			}
		}
		// Si no existe, lo añade como nuevo ítem
		items.add(item);
	}

	// Elimina un ítem del inventario
	public void quitarItem(String nombreItem) {
		// Si no existe retorna
		if (nombreItem == null || nombreItem.isEmpty())
			return;

		// Busca el ítem por nombre
		for (int i = 0; i < items.size(); i++) {
			Item itemActual = items.get(i);
			if (itemActual.getNombre().equals(nombreItem)) {
				if (itemActual.getCantidad() > 1) {
					// Si tiene más de 1, reduce la cantidad
					itemActual.setCantidad(itemActual.getCantidad() - 1);
				} else {
					// Si solo queda 1, elimina el ítem completamente
					items.remove(i);
				}
				break; // Terminamos el bucle tras encontrar el ítem
			}
		}
	}

	// Verifica si el inventario contiene un ítem con cantidad > 0
	public boolean contieneItem(String nombre) {
		for (Item item : items) {
			if (item.getNombre().equals(nombre) && item.getCantidad() > 0) {
				return true; // True si existe y tiene cantidad positiva
			}
		}
		return false; // False en caso contrario
	}

	// Obtiene la cantidad disponible de un ítem específico
	public int getCantidad(String nombre) {
		for (Item item : items) {
			if (item.getNombre().equals(nombre)) {
				return item.getCantidad();
			}
		}
		return 0;
	}

	// Devuelve una copia de la lista de ítems para evitar modificaciones externas
	public ArrayList<Item> getLista() {
		return new ArrayList<>(items);
	}
}