package vista;

import java.sql.Connection;

import bbdd.bbdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import modelo.Jugador;
import modelo.Tablero;

public class pantallaMenuController {
	private Connection con;
    private Jugador jugador;
    private Tablero tablero;
    
    public void initializeController(Jugador jugador, Tablero tablero) {
        this.con = bbdd.conectarBaseDatos();
        this.jugador = jugador;
        this.tablero = tablero;
    }
    
    @FXML
    private void handleNewGame(ActionEvent event) {
        System.out.println("Nueva partida iniciada");
        
    }
    
    @FXML
    private void handleContinueGame(ActionEvent event) {
        
    }
    
    @FXML
    private void handleSaveGame(ActionEvent event) {
        
    }
    
    @FXML
    private void handleRankingPlayers(ActionEvent event) {
        
    }
    
    @FXML
    private void handleQuitGame(ActionEvent event) {
        
    }
}