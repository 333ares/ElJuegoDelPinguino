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
import modelo.Inventario;
import modelo.Item;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.bbdd;
import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class pantallaPrincipalController {

	private Connection con;

	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;

	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;

	/*
	 * Controlador de la pantalla principal. Se encarga del inicio de sesión,
	 * registro de usuarios y de lanzar el juego si el login es exitoso.
	 */

	@FXML
	private void initialize() {
		// Establecer la conexión a la base de datos al inicializar el controlador
		con = bbdd.conectarBaseDatos();
	}

	// Inicio de sesión
	@FXML
	private void handleLogin(ActionEvent event) {
	    String username = userField.getText();
	    String password = passField.getText();

	    if (!username.isEmpty() && !password.isEmpty()) {
	        try {
	            String sqlCheck = "SELECT * FROM JUGADORES WHERE NICKNAME = '" + username + "' AND CONTRASEÑA = '" + password + "'";
	            ResultSet rs = bbdd.select(con, sqlCheck);

	            if (rs.next()) {
	                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaMenu.fxml"));
	                Parent root = loader.load();

	                pantallaMenuController menuController = loader.getController();
	                
	                menuController.initializeController(
	                    new Jugador(0, username, "Azul", new Pinguino(new Inventario(new ArrayList<>()))),
	                    new Tablero()
	                );
	                menuController.setConnection(con);

	                ((Stage) loginButton.getScene().getWindow()).close();
	                
	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));
	                stage.setTitle("Menú Principal");
	                stage.show();
	            } else {
	                System.out.println("Credenciales incorrectas");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


	// Registro de usuarios
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