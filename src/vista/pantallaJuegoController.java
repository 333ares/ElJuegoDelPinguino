package vista;

import java.util.List;
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
    	 // Asegurarse de que el jugador tenga un dado rápido en su inventario
        if (jugadorActual.getPinguino().getInv().contieneItem("dado rápido")) {
            // Usar el item
            jugadorActual.getPinguino().getInv().quitarItem("dado rápido");
            // Generar un número aleatorio entre 5 y 10 para el movimiento
            int movimiento = new Random().nextInt(6) + 5;
            // Actualizar la posición del jugador
            gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento);
            // Indicar el resultado en la interfaz
            rapido_t.setText("Has avanzado " + movimiento + " casillas.");
        } else {
            // Indicar que no hay dado rápido en el inventario
            rapido_t.setText("No tienes dado rápido.");
        }
        // Finalizar el turno actual
        gestorJugador.jugadorFinalizaTurno(jugadorActual);
    }

    @FXML
    private void handleLento() {
    	// Asegurarse de que el jugador tenga un dado lento en su inventario
        if (jugadorActual.getPinguino().getInv().contieneItem("dado lento")) {
            // Usar el item
            jugadorActual.getPinguino().getInv().quitarItem("dado lento");
            // Generar un número aleatorio entre 1 y 3 para el movimiento
            int movimiento = new Random().nextInt(3) + 1;
            // Actualizar la posición del jugador
            gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento);
            // Indicar el resultado en la interfaz
            lento_t.setText("Has avanzado " + movimiento + " casillas.");
        } else {
            // Indicar que no hay dado lento en el inventario
            lento_t.setText("No tienes dado lento.");
        }
        // Finalizar el turno actual
        gestorJugador.jugadorFinalizaTurno(jugadorActual);
    }

    @FXML
    private void handlePeces() {
    	 // Asegurarse de que el jugador tenga un pez en su inventario
        if (jugadorActual.getPinguino().getInv().contieneItem("pez")) {
            // Usar el item
            jugadorActual.getPinguino().getInv().quitarItem("pez");
            // Proteger al jugador de los osos
            jugadorActual.setProtegidoDelOso(true);
            // Indicar el resultado en la interfaz
            peces_t.setText("Estás protegido contra los osos.");
        } else {
            // Indicar que no hay peces en el inventario
            peces_t.setText("No tienes peces.");
        }
        // Finalizar el turno actual
        gestorJugador.jugadorFinalizaTurno(jugadorActual);
    }

    @FXML
    private void handleNieve() {
        List<Jugador> otrosJugadores = gestorJugador.getOtrosJugadores();
        if (!otrosJugadores.isEmpty()) {
            Jugador objetivo = otrosJugadores.get(0); // Suponiendo solo dos jugadores

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
