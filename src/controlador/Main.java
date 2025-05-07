package controlador;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Casilla;
import modelo.Inventario;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;
import vista.pantallaJuegoController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaPrincipal.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("El Juego del Pingüino");
        primaryStage.show();

        // Crear una matriz de casillas
        Casilla[] casillas = new Casilla[50];
        // Inicializar el tablero
        Tablero tablero = new Tablero(casillas, null, 0, null);

    
        Jugador jugador1 = new Jugador(0, "Jugador 1", null, null);
        Jugador jugador2 = new Jugador(0, "Jugador 2", null, null);
        tablero.getJugadores().add(jugador1);
        tablero.getJugadores().add(jugador2);

        pantallaJuegoController controller = (pantallaJuegoController) loader.getController();
        GestorJugador gestorJugador = new GestorJugador(jugador1, jugador2, tablero);
        GestorTablero gestorTablero = new GestorTablero(tablero);
        controller.initializeController(gestorJugador, gestorTablero, jugador1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}