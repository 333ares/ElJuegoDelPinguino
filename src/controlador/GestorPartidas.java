package controlador;

import java.util.ArrayList;

import modelo.Casilla;
import modelo.Tablero;

public class GestorPartidas {

	String urlBBDD;
	String username;
	String password;
	
	public void guardarPartida(Tablero t) {
		// Lógica para guardar el estado del tablero en la base de datos
		System.out.println("Partida guardada con éxito.");
	}
	
	public Tablero cargarPartida(){
		  // Lógica para cargar el estado del tablero desde la base de datos
		  System.out.println("Partida cargada con éxito.");
		    return new Tablero(new Casilla[50], new ArrayList<>(), 0, null);
	}

}
