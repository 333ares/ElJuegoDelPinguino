package vista;

import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import modelo.Jugador;
import modelo.Tablero;
import javafx.event.ActionEvent;

import javafx.scene.paint.Color;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

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
	private Text rapido_tP1;
	@FXML
	private Text rapido_tP2;
	@FXML
	private Text lento_tP1;
	@FXML
	private Text lento_tP2;
	@FXML
	private Text peces_tP1;
	@FXML
	private Text peces_tP2;
	@FXML
	private Text nieve_tP1;
	@FXML
	private Text nieve_tP2;
	@FXML
	private Text eventos;

	@FXML
	private GridPane tablero;
	@FXML
	private Circle P1;
	@FXML
	private Circle P2;

	private int p1Position = 0; // Tracks current position (from 0 to 49 in a 5x10 grid)
	private final int COLUMNS = 5;

	@FXML
	private void initialize() {
		eventos.setText("¡El juego ha comenzado!");
	}

	@FXML
	private void handleNewGame() {
		System.out.println("New game.");
		// TODO
	}

	@FXML
	private void handleSaveGame() {
		System.out.println("Saved game.");
		// TODO
	}

	@FXML
	private void handleLoadGame() {
		System.out.println("Loaded game.");
		// TODO
	}

	@FXML
	private void handleQuitGame() {
		System.out.println("Exit...");
		// TODO
	}

	@FXML
	public void actualizarVistaTablero() {
		// Obtener datos del modelo
		Tablero.TableroInfo info = gestorTablero.getTablero().getTableroInfo();

		// Limpiar el tablero gráfico (usando la variable de instancia)
		tablero.getChildren().clear(); // <<- ¡Aquí es donde se usa getChildren()!

		// Dibujar casillas
		for (int i = 0; i < info.casillas.length; i++) {
			// Usar clases de JavaFX, no de AWT
			javafx.scene.shape.Rectangle rect = new javafx.scene.shape.Rectangle(50, 50);
			rect.setFill(getColorCasilla(info.getTipoCasilla(i))); // Aplicar color

			// Posicionar en el GridPane
			GridPane.setRowIndex(rect, i / 10); // Filas
			GridPane.setColumnIndex(rect, i % 10); // Columnas

			tablero.getChildren().add(rect);

			// Dibujar jugadores
			List<Integer> indicesJugadores = info.getJugadoresEnCasilla(i);
			for (int indice : indicesJugadores) {
				Circle jugador = new Circle(15, getColorJugador(indice));
				GridPane.setRowIndex(jugador, i / 10);
				GridPane.setColumnIndex(jugador, i % 10);
				tablero.getChildren().add(jugador);
			}
		}
	}

	@FXML
	private Color getColorCasilla(String tipo) {
		switch (tipo) {
		case "Oso":
			return Color.BROWN;
		case "Agujero":
			return Color.BLACK;
		case "Trineo":
			return Color.WHITE;
		case "Evento":
			return Color.YELLOW;
		default:
			return Color.LIGHTGRAY;
		}
	}

	@FXML
	private Color getColorJugador(int indiceJugador) {
		// Obtener jugadores del modelo
		List<Jugador> jugadores = gestorTablero.getTablero().getJugadores();
		return Color.web(jugadores.get(indiceJugador).getColor());
	}

	@FXML
	public void handleDado(ActionEvent event) {
		Random rand = new Random();
		int diceResult = rand.nextInt(6) + 1;

		gestorTablero.actualizarMovimientoJugador(jugadorActual, diceResult);
		actualizarVistaTablero();

		dadoResultText.setText("Ha salido: " + diceResult);
		gestorJugador.jugadorFinalizaTurno(jugadorActual);
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

	@FXML
	public void handleRapido() {
		if (jugadorActual.getPinguino().getInv().contieneItem("dado rápido")) {
			jugadorActual.getPinguino().getInv().quitarItem("dado rápido");
			int movimiento = new Random().nextInt(6) + 5;
			gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento);
			rapido_t.setText("Has avanzado " + movimiento + " casillas.");
		} else {
			rapido_t.setText("No tienes dado rápido.");
		}
		gestorJugador.jugadorFinalizaTurno(jugadorActual);
	}

	@FXML
	public void handleLento() {
		if (jugadorActual.getPinguino().getInv().contieneItem("dado lento")) {
			jugadorActual.getPinguino().getInv().quitarItem("dado lento");
			int movimiento = new Random().nextInt(3) + 1;
			gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento);
			lento_t.setText("Has avanzado " + movimiento + " casillas.");
		} else {
			lento_t.setText("No tienes dado lento.");
		}
		gestorJugador.jugadorFinalizaTurno(jugadorActual);
	}

	@FXML
	public void handleNieve() {
		if (jugadorActual.getPinguino().getInv().contieneItem("bola de nieve")) {
			jugadorActual.getPinguino().getInv().quitarItem("bola de nieve");

			Jugador otroJugador = gestorJugador.getOtrosJugadores().get(0);
			int nuevaPosicion = otroJugador.getPosicion() - 3;
			if (nuevaPosicion < 0) {
				nuevaPosicion = 0;
			}
			otroJugador.setPosicion(nuevaPosicion);

			actualizarInterfazJugador(otroJugador);

			if (esJugador1()) {
				nieve_tP1.setText(otroJugador.getNombre() + " retrocede 3 casillas.");
			} else {
				nieve_tP2.setText(otroJugador.getNombre() + " retrocede 3 casillas.");
			}
		} else {
			if (esJugador1()) {
				nieve_tP1.setText("No tienes bolas de nieve.");
			} else {
				nieve_tP2.setText("No tienes bolas de nieve.");
			}
		}
		gestorJugador.jugadorFinalizaTurno(jugadorActual);
	}

	@FXML
	public void handlePeces() {
		if (jugadorActual.getPinguino().getInv().contieneItem("pez")) {
			jugadorActual.getPinguino().getInv().quitarItem("pez");
			jugadorActual.setProtegidoDelOso(true);
			peces_t.setText("Estás protegido contra los osos.");
		} else {
			peces_t.setText("No tienes peces.");
		}
		gestorJugador.jugadorFinalizaTurno(jugadorActual);
	}

	@FXML
	public void handleNieve() {
		// Sabemos que hay exactamente un otro jugador
		Jugador otroJugador = gestorJugador.getOtrosJugadores().get(0);
		int nuevaPosicion = otroJugador.getPosicion() - 3;
		if (nuevaPosicion < 0) {
			nuevaPosicion = 0;
		}
		otroJugador.setPosicion(nuevaPosicion);
		// Actualizar la interfaz gráfica para reflejar el cambio
		actualizarInterfazJugador(otroJugador);
		// Finalizar el turno
		gestorJugador.jugadorFinalizaTurno(jugadorActual);
	}

	@FXML
	private void actualizarInterfazJugador(Jugador jugador) {
		// Actualizar posición del jugador en el tablero
		int row = jugador.getPosicion() / COLUMNS;
		int col = jugador.getPosicion() % COLUMNS;

		// Suponiendo que tienes un Circle para cada jugador
		if (jugador.equals(jugadorActual)) {
			GridPane.setRowIndex(P1, row);
			GridPane.setColumnIndex(P1, col);
		} else {
			GridPane.setRowIndex(P2, row);
			GridPane.setColumnIndex(P2, col);
		}

		if (esJugador1()) {
			GridPane.setRowIndex(P1, row);
			GridPane.setColumnIndex(P1, col);
		} else {
			GridPane.setRowIndex(P2, row);
			GridPane.setColumnIndex(P2, col);
		}
		actualizarContadoresItems(jugador);
	}

	// Actualizar los contadores de items
	actualizarContadoresItems(jugador);
    }

	@FXML
	private void actualizarContadoresItems(Jugador jugador) {
		// Actualizar los textos que muestran la cantidad de items
		int peces = jugador.getPinguino().getInv().getCantidad("pez");
		int bolasNieve = jugador.getPinguino().getInv().getCantidad("bola de nieve");
		int dadosRapidos = jugador.getPinguino().getInv().getCantidad("dado rápido");
		int dadosLentos = jugador.getPinguino().getInv().getCantidad("dado lento");

		if (esJugador1()) {
			peces_tP1.setText("Peces: " + peces);
			nieve_tP1.setText("Bolas: " + bolasNieve);
			rapido_tP1.setText("Dado rápido: " + dadosRapidos);
			lento_tP1.setText("Dado lento: " + dadosLentos);
		}
	}

	peces_t.setText("Peces: "+peces);nieve_t.setText("Bolas de nieve: "+bolasNieve);rapido_t.setText("Dados rápidos: "+dadosRapidos);lento_t.setText("Dados lentos: "+dadosLentos);

	}

	@FXML
	public void initializeController(GestorJugador gestorJugador, GestorTablero gestorTablero, Jugador jugadorActual) {
		this.gestorJugador = gestorJugador;
		this.gestorTablero = gestorTablero;
		this.jugadorActual = jugadorActual;
	}
}