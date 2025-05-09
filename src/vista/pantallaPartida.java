package vista;

import java.io.IOException;
import java.util.ArrayList;

import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Casilla;
import modelo.Jugador;
import modelo.Tablero;

public class pantallaPartida {

    public void iniciarPartida() {
        Tablero tablero = new Tablero();
        GestorTablero gestorTablero = new GestorTablero(tablero);
        GestorJugador gestorJugador = new GestorJugador(null, null, tablero);

       
        Jugador jugadorActual = tablero.getJugadores().get(0);

        // Cargar la pantalla de juego y pasar las dependencias al controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
        Parent root;
        try {
            root = loader.load();
            pantallaJuegoController controller = loader.getController();
            controller.initializeController(gestorJugador, gestorTablero, jugadorActual);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
