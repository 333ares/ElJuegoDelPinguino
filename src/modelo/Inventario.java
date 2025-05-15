package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventario {
	private Map<String, Integer> items;

	/*
	 * Almacena ítems en un ArrayList. Permite añadir/eliminar ítems (por objeto o
	 * nombre), verificar su existencia y gestionar cantidades. Usa getters/setters
	 * para acceso a la lista. La eliminación por nombre reduce la cantidad o borra
	 * el ítem si es 1. Falta implementar getCantidad() completamente.
	 * 
	 */

	// CONSTRUCTOR
	public Inventario() {
		super();
		this.items = new HashMap<>();
	}


	public void añadirItem(String nombre, int cantidad) {
		 items.put(nombre, items.getOrDefault(nombre, 0) + cantidad);
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
        if (contieneItem(nombre)) {
            int cantidad = items.get(nombre);
            if (cantidad > 1) {
                items.put(nombre, cantidad - 1);
            } else {
                items.remove(nombre);
            }
        }
    }

    public boolean contieneItem(String nombre) {
        return items.containsKey(nombre) && items.get(nombre) > 0;
    }


    public int getCantidad(String nombre) {
        return items.getOrDefault(nombre, 0);
    }

    public ArrayList<Item> getLista() {
        ArrayList<Item> lista = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            lista.add(new Item(entry.getKey(), entry.getValue()));
        }
        return lista;
    }
    

}
