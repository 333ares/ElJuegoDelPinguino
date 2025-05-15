package vista;

import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import modelo.Jugador;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class pantallaJuegoController {

	private GestorJugador gestorJugador;
	private GestorTablero gestorTablero;
	private Jugador jugadorActual;

	@FXML
	private MenuItem newGame;
	@FXML
	private MenuItem saveGame;
	@FXML
	private MenuItem loadGame;
	@FXML
	private MenuItem quitGame;

	@FXML
	private Button dado;
	@FXML
	private Button rapido;
	@FXML
	private Button lento;
	@FXML
	private Button peces;
	@FXML
	private Button nieve;

	@FXML
	private Text dadoResultText;
	@FXML
	private Text rapido_t;
	@FXML
	private Text lento_t;
	@FXML
	private Text peces_t;
	@FXML
	private Text nieve_t;
	@FXML
	private Text eventos;

	@FXML
	private GridPane tablero;
	@FXML
	private Circle P1;

	private int p1Position = 0; // Tracks current position (from 0 to 49 in a 5x10 grid)
	private final int COLUMNS = 5;

	@FXML
	private void initialize() {
		eventos.setText("¡El juego ha comenzado!");
		// actualizarTablero();
	}

	@FXML
	private void handleNewGame() {
		eventos.setText("New game.");
		// TODO
	}

	@FXML
	private void handleSaveGame() {
		eventos.setText("Saved game.");
		// TODO
	}

	@FXML
	private void handleLoadGame() {
		eventos.setText("Loaded game.");
		// TODO
	}

	@FXML
	private void handleQuitGame() {
		eventos.setText("Exit...");
		// TODO
	}

	@FXML
	private void handleDado(ActionEvent event) {
		Random rand = new Random();
		int diceResult = rand.nextInt(6) + 1;

		// Update the Text
		dadoResultText.setText("Ha salido: " + diceResult);

		// Update the position
		moveP1(diceResult);
	}

	private void moveP1(int steps) {
		p1Position += steps;

		// Bound player
		if (p1Position >= 50) {
			p1Position = 49; // 5 columns * 10 rows = 50 cells (index 0 to 49)
		}

		// Check row and column
		int row = p1Position / COLUMNS;
		int col = p1Position % COLUMNS;

		// Change P1 property to match row and column
		GridPane.setRowIndex(P1, row);
		GridPane.setColumnIndex(P1, col);
	}

	private void actualizarTablero() {
		if (gestorTablero == null || gestorTablero.getTablero() == null || tablero == null) {
			System.err.println("Error: Componentes no inicializados para actualizar tablero");
			return;
		}

		// Limpiar solo las imágenes dinámicas
		List<Node> toRemove = tablero.getChildren().stream()
				.filter(node -> node instanceof ImageView && node.getId() == null).collect(Collectors.toList());
		tablero.getChildren().removeAll(toRemove);

		// Añadir imágenes con verificación de recursos
		for (int i = 0; i < 50; i++) {
			ImageView imageView = new ImageView();
			try {
				String imagePath = obtenerRutaImagenCasilla(i);
				InputStream is = getClass().getResourceAsStream(imagePath);
				if (is == null) {
					throw new IOException("Recurso no encontrado: " + imagePath);
				}
				imageView.setImage(new Image(is));
				imageView.setFitWidth(50);
				imageView.setFitHeight(50);
				tablero.add(imageView, i % 5, i / 5);
			} catch (Exception e) {
				System.err.println("Error cargando imagen para casilla " + i + ": " + e.getMessage());
				// Imagen por defecto
				imageView.setImage(new Image(getClass().getResourceAsStream("/casillanormal.png")));
				tablero.add(imageView, i % 5, i / 5);
			}
		}
	}

	private String obtenerRutaImagenCasilla(int posicion) {
		String tipo = gestorTablero.getTablero().getCasillaTipo(posicion);
		switch (tipo) {
		case "Oso":
			return "/oso.png";
		case "Agujero":
			return "/agujero.png";
		case "Trineo":
			return "/trineo.png";
		case "Evento":
			return "/evento.png";
		default:
			return "/casillanormal.png";
		}
	}

	@FXML
	public void handleRapido() {
		if (jugadorActual.getPinguino().getInv().contieneItem("dado rápido")) {
			jugadorActual.getPinguino().getInv().quitarItem("dado rápido");
			int movimiento = new Random().nextInt(6) + 5;
			gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento);

			eventos.setText(eventos.getText() + "\nHas avanzado " + movimiento + " casillas.");
		} else {
			eventos.setText(eventos.getText() + "\nNo tienes dado rápido.");
		}

		actualizarContadoresItems();
	}

	@FXML
	public void handleLento() {
		if (jugadorActual.getPinguino().getInv().contieneItem("dado lento")) {
			jugadorActual.getPinguino().getInv().quitarItem("dado lento");
			int movimiento = new Random().nextInt(3) + 1;
			gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento);

			eventos.setText(eventos.getText() + "\nHas avanzado " + movimiento + " casillas.");
		} else {
			eventos.setText(eventos.getText() + "\nNo tienes dado lento.");

		}

		actualizarContadoresItems();
	}

	@FXML
	public void handleNieve() {
		if (jugadorActual.getPinguino().getInv().contieneItem("bola de nieve")) {
			jugadorActual.getPinguino().getInv().quitarItem("bola de nieve");

			eventos.setText(eventos.getText() + "\nNo hay un segundo jugador para afectar.");
		} else {
			eventos.setText(eventos.getText() + "\nNo tienes bolas de nieve.");
		}

		actualizarContadoresItems();
	}

	@FXML
	public void handlePeces() {
		if (jugadorActual.getPinguino().getInv().contieneItem("pez")) {
			jugadorActual.getPinguino().getInv().quitarItem("pez");
			jugadorActual.setProtegidoDelOso(true);

			eventos.setText(eventos.getText() + "\nEstás protegido contra los osos.");
		} else {
			eventos.setText(eventos.getText() + "\nNo tienes peces.");
		}

		actualizarContadoresItems();
	}

	private void actualizarInterfazJugador() {
		// Actualizar posición del jugador en el tablero
		int row = jugadorActual.getPosicion() / COLUMNS;
		int col = jugadorActual.getPosicion() % COLUMNS;

		// Hay un Circle para cada jugador
		GridPane.setRowIndex(P1, row);
		GridPane.setColumnIndex(P1, col);

		actualizarContadoresItems();
	}

	private void actualizarContadoresItems() {
		// Actualizar los textos que muestran la cantidad de items
		int peces = jugadorActual.getPinguino().getInv().getCantidad("pez");
		int bolasNieve = jugadorActual.getPinguino().getInv().getCantidad("bola de nieve");
		int dadosRapidos = jugadorActual.getPinguino().getInv().getCantidad("dado rápido");
		int dadosLentos = jugadorActual.getPinguino().getInv().getCantidad("dado lento");

		peces_t.setText("Peces: " + peces);
		nieve_t.setText("Bolas: " + bolasNieve);
		rapido_t.setText("Dado rápido: " + dadosRapidos);
		lento_t.setText("Dado lento: " + dadosLentos);

	}

	public void initializeController(GestorJugador gestorJugador, GestorTablero gestorTablero, Jugador jugadorActual) {
		this.gestorJugador = gestorJugador;
		this.gestorTablero = gestorTablero;
		this.jugadorActual = jugadorActual;

		// Configuración inicial de la interfaz
		eventos.setText("¡El juego ha comenzado!");
		actualizarContadoresItems();

		// Actualización diferida del tablero
		Platform.runLater(() -> {
			actualizarTablero();
			actualizarInterfazJugador();
		});
	}

}
