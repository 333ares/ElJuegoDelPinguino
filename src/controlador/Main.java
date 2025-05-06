package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import modelo.Casilla;
import modelo.Tablero;
import vista.pantallaJuegoController;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//System.out.println(getClass().getResource("/pantallaPrincipal.fxml"));
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

        // Crear jugadores y agregarlos al tablero (ya se hace en el constructor de Tablero)
        // tablero.initializeJugadores();

        pantallaJuegoController controller = (pantallaJuegoController) loader.getController();
        GestorJugador gestorJugador = new GestorJugador(tablero.getJugadores().get(0), tablero);
        controller.initializeController(gestorJugador, null, tablero.getJugadores().get(0));
    }
	

    public static void main(String[] args) {
        launch(args);
 
    }
}
