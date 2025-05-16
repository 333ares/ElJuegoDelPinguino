package vista;

import java.io.IOException;
import java.sql.Connection;

import bbdd.bbdd;
import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.Tablero;

public class PantallaMenuController {
	private Connection con;
    private Jugador jugadorActual;

    public void setJugador(Jugador jugador) {
        this.jugadorActual = jugador;
    }

    @FXML
    private void initialize() {
        con = bbdd.conectarBaseDatos();
    }
    
    @FXML
    private void handleNewGame(ActionEvent event) {
        Tablero tablero = new Tablero();
        GestorTablero gestorTablero = new GestorTablero(tablero);
        tablero.setGestorTablero(gestorTablero);

        GestorJugador gestorJugador = new GestorJugador(jugadorActual, tablero);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
            Parent root = loader.load();

            pantallaJuegoController controller = loader.getController();
            controller.initializeController(gestorJugador, gestorTablero, jugadorActual);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Juego en curso");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
