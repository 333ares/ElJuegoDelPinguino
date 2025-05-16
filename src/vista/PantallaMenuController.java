package vista;

import java.sql.Connection;

import bbdd.bbdd;
import javafx.fxml.FXML;
import modelo.Jugador;

public class PantallaMenuController {
	private Connection con;
    private Jugador jugadorActual;

    public void setJugador(Jugador jugador) {
        this.jugadorActual = jugador;
    }

    @FXML
    private void initialize() {
        con = bbdd.conectarBaseDatos(); // Conexión activa
    }
    
    
}
