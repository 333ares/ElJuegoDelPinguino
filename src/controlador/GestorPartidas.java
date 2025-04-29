package controlador;

import java.util.ArrayList;

import modelo.Casilla;
import modelo.Tablero;

public class GestorPartidas {

	String urlBBDD;
	String username;
	String password;
	
	public void guardarPartida(Tablero t) {
		 // Aquí iría la lógica para conectar con la base de datos y guardar el estado
	    // del tablero, las posiciones de los jugadores, sus inventarios, etc.
		// Lógica para guardar el estado del tablero en la base de datos
		System.out.println("Partida guardada con éxito.");
	}
	
	public Tablero cargarPartida(){
		  // Lógica para cargar el estado del tablero desde la base de datos
		   Tablero tableroCargado = new Tablero(new Casilla[50], new ArrayList<>(), 0, null);
		    // Aquí iría la lógica para conectar con la base de datos y cargar el estado
		    // del tablero, las posiciones de los jugadores, sus inventarios, etc.
		    System.out.println("Cargando partida...");
		    System.out.println("Partida cargada con éxito.");
		    return tableroCargado;
	}

}
