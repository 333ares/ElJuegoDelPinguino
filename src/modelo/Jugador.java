package modelo;

import java.util.ArrayList;
import java.util.Random;

import controlador.GestorJugador;
import vista.GestorMensajes;

public class Jugador {

	int posicion;
	String nombre;
	String color;
	Pinguino pinguino;
	Tablero tablero;
	private GestorMensajes gestorMensajes;
	/*
	 * Clase que representa un jugador con posición, nombre, color y un Pinguino
	 * asociado. Contiene métodos para moverse por el tablero (0-49 posiciones) y
	 * lanzar dados (1-6). Gestiona protección contra osos (protegidoDelOso) y usa
	 * getters/setters para acceder a sus atributos. El constructor inicializa los
	 * datos básicos y asocia un tablero (aunque la asignación actual tiene un bug).
	 * 
	 */

	// CONSTRUCTOR
	public Jugador(int posicion, String nombre, String color, Pinguino pinguino) {
		super();
		this.posicion = posicion;
		this.nombre = nombre;
		this.color = color;
		this.pinguino = pinguino != null ? pinguino : new Pinguino(new Inventario(null));
		this.tablero = tablero;

	}

	// GETTERS Y SETTERS

	public GestorMensajes getGestorMensajes() {
		return gestorMensajes;
	}

	public void setGestorMensajes(GestorMensajes gestorMensajes) {
		this.gestorMensajes = gestorMensajes;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Pinguino getPinguino() { // El método getPinguino se utiliza para obtener el objeto Pinguino asociado al
									// jugador.
		return pinguino;
	}

	public Pinguino setPinguino(Pinguino pinguino) {
		return pinguino;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// FUNCIONES
	public void moverPosicion(int nuevaPosicion) {
		// El if se utiliza para asegurar que la nueva posición del jugador esté dentro
		// de los límites del tablero (0 a 49, ya que hay 50 casillas).
		if (nuevaPosicion >= 0 && nuevaPosicion < 50) {
			this.posicion = nuevaPosicion;
		}

	}

	// Método para proteger al jugador del Oso
	private boolean protegidoDelOso = false;
	// Controla si el jugador está protegido contra osos.

	public void setProtegidoDelOso(boolean protegidoDelOso) {
		this.protegidoDelOso = protegidoDelOso;
	}

	public boolean isProtegidoDelOso() {
		return protegidoDelOso;
	}

	public String serializar() {
		StringBuilder sb = new StringBuilder();
		sb.append(posicion).append(";").append(protegidoDelOso).append(";").append("INV:");

		// Serializar inventario
		if (pinguino != null && pinguino.getInv() != null) {
			for (Item item : pinguino.getInv().getLista()) {
				sb.append(item.getNombre().replace(" ", "_")).append("-").append(item.getCantidad()).append(",");
			}
		}

		return sb.toString();
	}

	public void deserializar(String estado) {
		String[] partes = estado.split(";");
		if (partes.length > 0) {
			this.posicion = Integer.parseInt(partes[0]);
		}
		if (partes.length > 1) {
			this.protegidoDelOso = Boolean.parseBoolean(partes[1]);
		}
		if (partes.length > 2 && partes[2].startsWith("INV:")) {
			String[] items = partes[2].substring(4).split(",");
			for (String item : items) {
				if (!item.isEmpty()) {
					String[] datos = item.split("-");
					String nombre = datos[0].replace("_", " ");
					int cantidad = Integer.parseInt(datos[1]);

					if (pinguino == null) {
						pinguino = new Pinguino(new Inventario(new ArrayList<>()));
					}
					if (pinguino.getInv() == null) {
						pinguino.setInv(new Inventario(new ArrayList<>()));
					}

					pinguino.getInv().añadirItem(new Item(nombre, cantidad));
				}
			}
		}
	}
}
