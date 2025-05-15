package modelo;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> items;

    public Inventario(ArrayList<Item> items) {
        this.items = items != null ? items : new ArrayList<>();
    }

    public void añadirItem(Item item) {
        // Buscar si ya existe el item
        for (Item i : items) {
            if (i.getNombre().equals(item.getNombre())) {
                i.setCantidad(i.getCantidad() + item.getCantidad());
                return;
            }
        }
        // Si no existe, añadirlo
        items.add(item);
    }
    public void quitarItem(String nombreItem) {
        if (nombreItem == null || nombreItem.isEmpty()) return;
        
        for (int i = 0; i < items.size(); i++) {
            Item itemActual = items.get(i);
            if (itemActual.getNombre().equals(nombreItem)) {
                if (itemActual.getCantidad() > 1) {
                    itemActual.setCantidad(itemActual.getCantidad() - 1);
                } else {
                    items.remove(i);
                }
                break;
            }
        }
    }

    public boolean contieneItem(String nombre) {
        for (Item item : items) {
            if (item.getNombre().equals(nombre) && item.getCantidad() > 0) {
                return true;
            }
        }
        return false;
    }

    public int getCantidad(String nombre) {
        for (Item item : items) {
            if (item.getNombre().equals(nombre)) {
                return item.getCantidad();
            }
        }
        return 0;
    }

    public ArrayList<Item> getLista() {
        return new ArrayList<>(items);
    }
}