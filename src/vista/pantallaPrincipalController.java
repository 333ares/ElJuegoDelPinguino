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

		System.out.println("Login pressed: " + username + " / " + password);

		// Basic check (just for demo, replace with real login logic)
		if (!username.isEmpty() && !password.isEmpty()) {
			try {
				// Cargar pantalla de juego
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
				Parent root = loader.load();

				// Configurar controlador del juego
				pantallaJuegoController juegoController = loader.getController();

				// En tu método que inicializa el juego:
				Tablero tablero = new Tablero();
				GestorTablero gestorTablero = new GestorTablero(tablero);
				tablero.setGestorTablero(gestorTablero); // Esto es crucial para los agujeros

				// Crear jugador con inventario
				ArrayList<Item> itemsIniciales = new ArrayList<>();
				itemsIniciales.add(new Item("dado rápido", 1));
				itemsIniciales.add(new Item("dado lento", 1));
				itemsIniciales.add(new Item("bola de nieve", 2));
				itemsIniciales.add(new Item("pez", 3));

				Inventario inventario = new Inventario(itemsIniciales);
				Pinguino pinguino = new Pinguino(inventario);
				Jugador jugador = new Jugador(0, "Jugador1", "Azul", pinguino);

				// Crear gestor de jugador y establecer la referencia en el tablero
				GestorJugador gestorJugador = new GestorJugador(jugador, tablero);
				tablero.setGestorJugador(gestorJugador);

				// Inicializar controlador
				juegoController.initializeController(gestorJugador, gestorTablero, jugador, tablero);

				// Mostrar pantalla de juego
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(new Scene(root));
				stage.setTitle("Menu principal");
			} catch (IOException e) {
				e.printStackTrace(); // Mostrar mensaje de error

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