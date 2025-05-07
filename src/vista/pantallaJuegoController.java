package vista;

import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import modelo.Jugador;
import javafx.event.ActionEvent;
import java.util.Random;

public class pantallaJuegoController {

	@FXML private GestorJugador gestorJugador;
	@FXML private GestorTablero gestorTablero;
	@FXML private Jugador jugadorActual;

	@FXML private MenuItem newGame;
	@FXML private MenuItem saveGame;
	@FXML private MenuItem loadGame;
	@FXML private MenuItem quitGame;

    @FXML private Button dado;
    @FXML private Button rapido;
    @FXML private Button lento;
    @FXML private Button peces;
    @FXML private Button nieve;

    @FXML private Text dadoResultText;
    @FXML private Text rapido_t;
    @FXML private Text lento_t;
    @FXML private Text peces_t;
    @FXML private Text nieve_t;
    @FXML private Text eventos;

    @FXML private GridPane tablero;
    @FXML private Circle P1;
    @FXML private Circle P2;
    @FXML private Circle P3;
    @FXML private Circle P4;

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
    public void handleDado(ActionEvent event) {
    	 Random rand = new Random();
    	    int diceResult = rand.nextInt(6) + 1;

    	    // Actualizar la posición del jugador usando el gestor de tablero
    	    gestorTablero.actualizarMovimientoJugador(jugadorActual, diceResult);

    	    // Actualizar el texto de resultado del dado
    	    dadoResultText.setText("Ha salido: " + diceResult);

    	    // Finalizar el turno actual y cambiar al siguiente jugador
    	    gestorJugador.jugadorFinalizaTurno(jugadorActual);
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

        // Actualizar los contadores de items
        actualizarContadoresItems(jugador);
    }

    private void actualizarContadoresItems(Jugador jugador) {
        // Actualizar los textos que muestran la cantidad de items
        int peces = jugador.getPinguino().getInv().getCantidad("pez");
        int bolasNieve = jugador.getPinguino().getInv().getCantidad("bola de nieve");
        int dadosRapidos = jugador.getPinguino().getInv().getCantidad("dado rápido");
        int dadosLentos = jugador.getPinguino().getInv().getCantidad("dado lento");

        peces_t.setText("Peces: " + peces);
        nieve_t.setText("Bolas de nieve: " + bolasNieve);
        rapido_t.setText("Dados rápidos: " + dadosRapidos);
        lento_t.setText("Dados lentos: " + dadosLentos);
    }
    
    public void initializeController(GestorJugador gestorJugador, GestorTablero gestorTablero, Jugador jugadorActual) {
        this.gestorJugador = gestorJugador;
        this.gestorTablero = gestorTablero;
        this.jugadorActual = jugadorActual;
    }
}