package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

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
		// Sobrescribe el método start() de Application, que es el punto de entrada de
		// toda aplicación JavaFX. Recibe un Stage (ventana principal) como parámetro.

		// System.out.println(getClass().getResource("/pantallaPrincipal.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaPrincipal.fxml"));
		// Crea un FXMLLoader para cargar el archivo FXML que define la interfaz gráfica
		// principal.
		Parent root = loader.load();
		// Carga la jerarquía de componentes gráficos definidos en el FXML y los asigna
		// a un nodo raíz (Parent).

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("El Juego del Pingüino");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
