package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import modelo.Inventario;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;
import vista.pantallaJuegoController;

/*
 * Application: Clase base para aplicaciones JavaFX
 * FXMLLoader: Para cargar archivos FXML (interfaces gráficas)
 * Scene y Parent: Para la estructura de la interfaz gráfica
 * Stage: Representa la ventana principal de la aplicación
 * 
 */
public class Main extends Application {

	/*
	 * Esta clase es el lanzador de la aplicación gráfica, encargándose de cargar la
	 * ventana inicial. Su función es puramente de inicialización, mientras que la
	 * lógica del juego estaría en otras clases como GestorTablero y el controlador
	 * asociado al FXML.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
	    // Cargar primero la pantalla principal (login)
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaPrincipal.fxml"));
	    Parent root = loader.load();
	    
	    Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("El Juego del Pingüino - Login");
	    primaryStage.show();
	}
		

		
	


	public static void main(String[] args) {
		launch(args);
	}
}
