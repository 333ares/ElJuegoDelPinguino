package vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbdd.bbdd;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class pantallaPrincipalController {

	private Connection con;
	@FXML
	private MenuItem newGame;
	@FXML
	private MenuItem saveGame;
	@FXML
	private MenuItem loadGame;
	@FXML
	private MenuItem quitGame;

	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;

	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;

	@FXML
	private void initialize() {
		// Establecer la conexión a la base de datos al inicializar el controlador
		con = bbdd.conectarBaseDatos();
	}

	@FXML
	private void handleNewGame() {
		System.out.println("New Game clicked");
		// TODO
	}

	@FXML
	private void handleSaveGame() {
		System.out.println("Save Game clicked");
		// TODO
	}

	@FXML
	private void handleLoadGame() {
		System.out.println("Load Game clicked");
		// TODO
	}

	@FXML
	private void handleQuitGame() {
		System.out.println("Quit Game clicked");
		// TODO
		System.exit(0);
	}

	@FXML
	private void handleLogin(ActionEvent event) {
		String username = userField.getText();
        String password = passField.getText();

        System.out.println("Login pressed: " + username + " / " + password);

        // Basic check (just for demo, replace with real login logic)
        if (!username.isEmpty() && !password.isEmpty()) {
            try {
            	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
         	    Parent root = loader.load();
         	    Stage stage = (Stage) loginButton.getScene().getWindow();
         	    stage.setScene(new Scene(root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please. Enter user and password.");
        }
    }

	@FXML
	private void handleRegister() {
		String username = userField.getText();
		String password = passField.getText();
		int partidas_jugadas = 0;

		if (!username.isEmpty() && !password.isEmpty()) {
			// Consulta SQL para verificar si el usuario ya existe
			String sqlCheck = "SELECT * FROM jugadores WHERE nickname = '" + username + "'";

			ResultSet rs = bbdd.select(con, sqlCheck);

			try {
				if (!rs.next()) {
					// Usuario no existe, se puede registrar
					String sqlInsert = "INSERT INTO jugadores (nickname, contraseña, partidas_jugadas) VALUES ('"
							+ username + "', '" + password + "', '" + partidas_jugadas + "')";
					bbdd.insert(con, sqlInsert);
					System.out.println("Registro exitoso");
				} else {
					// Usuario ya existe
					System.out.println("El usuario ya existe");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Por favor, ingrese usuario y contraseña.");
		}

	}
}