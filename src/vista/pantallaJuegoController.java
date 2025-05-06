package vista;

import java.util.Random;

import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import modelo.Jugador;
import controlador.GestorJugador;

public class pantallaJuegoController {

	
	 private GestorJugador gestorJugador;
	    private GestorTablero gestorTablero;
	    private Jugador jugadorActual;

	    // Método para inicializar el controlador con las dependencias necesarias
	    public void initializeController(GestorJugador gestorJugador, GestorTablero gestorTablero, Jugador jugadorActual) {
	        this.gestorJugador = gestorJugador;
	        this.gestorTablero = gestorTablero;
	        this.jugadorActual = jugadorActual;
	    }
	    
    // Menu items
    @FXML private MenuItem newGame;
    @FXML private MenuItem saveGame;
    @FXML private MenuItem loadGame;
    @FXML private MenuItem quitGame;

    // Buttons
    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    // Texts
    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    // Game board and player pieces
    @FXML private GridPane tablero;
    @FXML private Circle P1;
    @FXML private Circle P2;
    @FXML private Circle P3;
    @FXML private Circle P4;
    
    //ONLY FOR TESTING!!!
    private int p1Position = 0; // Tracks current position (from 0 to 49 in a 5x10 grid)
    private final int COLUMNS = 5;

    @FXML
    private void initialize() {
        // This method is called automatically after the FXML is loaded
        // You can set initial values or add listeners here
        eventos.setText("¡El juego ha comenzado!");
    }

    // Button and menu actions

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
    private void handleDado(ActionEvent event) {
    	  Random rand = new Random();
    	    int diceResult = rand.nextInt(6) + 1;

    	    // Actualizar la posición del jugador usando el gestor de tablero
    	    gestorTablero.actualizarMovimientoJugador(jugadorActual, diceResult);

    	   
    	    dadoResultText.setText("Ha salido: " + diceResult);
    }

    private void moveP1(int steps) {
        p1Position += steps;

        //Bound player
        if (p1Position >= 50) {
            p1Position = 49; // 5 columns * 10 rows = 50 cells (index 0 to 49)
        }

        //Check row and column
        int row = p1Position / COLUMNS;
        int col = p1Position % COLUMNS;

        //Change P1 property to match row and column
        GridPane.setRowIndex(P1, row);
        GridPane.setColumnIndex(P1, col);
    }

    @FXML
    private void handleRapido() {
    	   // Lógica para usar un dado rápido
        gestorJugador.jugadorUsaItem(jugadorActual, "dado rápido");
    }

    @FXML
    private void handleLento() {
    	   // Lógica para usar un dado lento
        gestorJugador.jugadorUsaItem(jugadorActual, "dado lento");
    }

    @FXML
    private void handlePeces() {
    	  // Lógica para usar un pez
        gestorJugador.jugadorUsaItem(jugadorActual, "pez");
    }

    @FXML
    private void handleNieve() {
    	  if (!otrosJugadores.isEmpty()) {
    	        Jugador objetivo = null;
    	        if (jugadorActual.getNombre().equals(otrosJugadores.get(0).getNombre())) {
    	            objetivo = otrosJugadores.get(1); // El objetivo es el segundo jugador
    	        } else {
    	            objetivo = otrosJugadores.get(0); // El objetivo es el primer jugador
    	        }

    	        int nuevaPosicion = objetivo.getPosicion() - 3; // Retroceder 3 casillas
    	        if (nuevaPosicion < 0) nuevaPosicion = 0;
    	        objetivo.setPosicion(nuevaPosicion);
    	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	        alert.setContentText(objetivo.getNombre() + " retrocede 3 casillas.");
    	        alert.showAndWait();
    	    } else {
    	        Alert alert = new Alert(Alert.AlertType.WARNING);
    	        alert.setContentText("No hay otros jugadores para afectar.");
    	        alert.showAndWait();
    	    }
    }
}
