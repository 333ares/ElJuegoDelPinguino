package controlador;
import modelo.Tablero;

public class GestorPartidas {

    String urlBDD;
    String username;
    String password;

    public GestorPartidas(String urlBDD, String username, String password) {
        this.urlBDD = urlBDD;
        this.username = username;
        this.password = password;
    }

    public void guardarPartida(Tablero t) {
        // Logica para guardar la Partida
    }

    public Tablero cargarPartida() {
		return null;
       //Logica para cargar la partida guardada con anterioridad
    }
    }

