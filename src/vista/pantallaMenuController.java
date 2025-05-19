package vista;

import java.sql.Connection;

import bbdd.bbdd;
import modelo.Jugador;
import modelo.Tablero;

public class pantallaMenuController {
	private Connection con;
    private Jugador jugador;
    private Tablero tablero;
    
    public void initializeController(Jugador jugador, Tablero tablero) {
        this.con = bbdd.conectarBaseDatos();
        this.jugador = jugador;
        this.tablero = tablero;
    }
}