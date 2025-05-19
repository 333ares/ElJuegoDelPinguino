package vista;

import java.io.IOException;
import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.Tablero;

public class pantallaPartida {

	/*
	 * Esta clase representa el punto de entrada para iniciar una nueva partida.
	 * 
	 * Se encarga de inicializar el modelo (Tablero y Jugador), cargar la vista
	 * (FXML) y vincularla con el controlador correspondiente.
	 */
	public void iniciarPartida() {
		Tablero tablero = new Tablero();
		GestorTablero gestorTablero = new GestorTablero(tablero);
		Jugador jugadorActual = tablero.inicializarJugador();

		// Cargar la pantalla de juego y pasar las dependencias al controlador
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
		Parent root;
		try {
			root = loader.load();
			pantallaJuegoController controller = loader.getController();
			controller.initializeController(new GestorJugador(jugadorActual, tablero), gestorTablero, jugadorActual,
					tablero);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}