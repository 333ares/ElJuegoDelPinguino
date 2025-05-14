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
		for (int i = 0; i < 50; i++) {
			String tipoCasilla = gestorTablero.getTablero().getCasillaTipo(i);
			ImageView imageView = new ImageView();
			switch (tipoCasilla) {
			case "Oso":
				imageView.setImage(new Image("resources/oso"));
				break;
			case "Agujero":
				imageView.setImage(new Image("resources/agujero"));
				break;
			case "Trineo":
				imageView.setImage(new Image("resources/trineo"));
				break;
			case "Evento":
				imageView.setImage(new Image("resources/evento"));
				break;
			default:
				imageView.setImage(new Image("resources/casillanormal"));
				break;
			}
			imageView.setFitWidth(50);
			imageView.setFitHeight(50);
			tablero.add(imageView, i % 5, i / 5); // Añade la imagen al GridPane
		}
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

		nieve_t.setText("No hay un segundo jugador para afectar.");
			} else {
				nieve_t.setText("No tienes bolas de nieve.");
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
	}
}