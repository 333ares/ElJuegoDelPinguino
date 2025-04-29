package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import vista.PantallaMenu;

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
	}

    public static void main(String[] args) {
        PantallaMenu pantallaMenu = new PantallaMenu();
        pantallaMenu.mostrarMenu();
        // Lógica para gestionar la opción seleccionada
 
    }
}
