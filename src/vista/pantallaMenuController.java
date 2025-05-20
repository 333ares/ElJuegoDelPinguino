package vista;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.bbdd;
import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import modelo.Inventario;
import modelo.Item;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;
import javafx.scene.Node;

public class pantallaMenuController {
	private Connection con;
    private Jugador jugador;
    private Tablero tablero;
    
    public void initializeController(Jugador jugador, Tablero tablero) {
        this.jugador = jugador;
        this.tablero = tablero;
    }
    
    public void setConnection(Connection con) {
        this.con = con;
    }
    
    private String serializarJugador() {
	    // Implementa la lógica para convertir el estado del jugador a String
	    return jugador.serializar();
	}
	
	private String serializarTablero() {
	    // Implementa la lógica para convertir el estado del tablero a String
	    return tablero.serializar();
	}
	
    @FXML
    private void handleNewGame(ActionEvent event) {
        try {
            Tablero tablero = new Tablero();
            String nickname = jugador.getNombre();
            
            // Luego el gestor de tablero
            GestorTablero gestorTablero = new GestorTablero(tablero);
            
            // Asignar el gestor al tablero
            tablero.setGestorTablero(gestorTablero);
            
            // Ahora inicializar las casillas
            tablero.inicializarTableroCompleto();
            
            // Crear jugador con inventario inicial
            ArrayList<Item> itemsIniciales = new ArrayList<>();
            itemsIniciales.add(new Item("dado rápido", 0));
            itemsIniciales.add(new Item("dado lento", 0));
            itemsIniciales.add(new Item("bola de nieve", 0));
            itemsIniciales.add(new Item("pez", 0));
            
            Inventario inventario = new Inventario(itemsIniciales);
            Pinguino pinguino = new Pinguino(inventario);
            Jugador jugadorNuevo = new Jugador(0, nickname, "Azul", pinguino);
            
            String estadoTablero = serializarTablero();
	        String estadoJugador = serializarJugador();
            
            // 3. Insertar nueva partida en la BD
	        String sqlInsert = "INSERT INTO PARTIDAS (NUM_PARTIDA, FECHA, HORA, ESTADO_TABLERO, ESTADO_PARTIDA) VALUES (" +
	                 "JP_S01.nextval, " +
	                 "CURRENT_DATE, " +
	                 "TO_CHAR(CURRENT_TIMESTAMP, 'HH24:MI:SS'), " +
	                 "'" + estadoTablero + "', " +
	                 "'" + estadoJugador + "')";
            
            bbdd.insert(con, sqlInsert);
            
            // 4. Actualizar contador de partidas del jugador
            String sqlUpdate = "UPDATE JUGADORES SET PARTIDAS_JUGADAS = PARTIDAS_JUGADAS + 1 " +
                              "WHERE NICKNAME = '" + nickname + "'";
            bbdd.update(con, sqlUpdate);
            
            // 5. Configurar y mostrar la pantalla de juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
            Parent root = loader.load();
            
            GestorJugador gestorJugador = new GestorJugador(jugadorNuevo, tablero);
            pantallaJuegoController juegoController = loader.getController();
            juegoController.initializeController(gestorJugador, gestorTablero, jugadorNuevo, tablero);
            juegoController.setConnection(con);

            // Cambiar a la pantalla de juego
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Juego en curso");
            
        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la pantalla de juego: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoadGame(ActionEvent event) {
        try {
            String sql = "SELECT * FROM PARTIDAS WHERE NICKNAME = '" + jugador.getNombre() + "' ORDER BY FECHA DESC, HORA DESC";
            ResultSet rs = bbdd.select(con, sql);

            if (rs.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
                Parent root = loader.load();

                // Cargar partida existente
                String estadoTablero = rs.getString("ESTADO_TABLERO");
                String estadoPartida = rs.getString("ESTADO_PARTIDA");
                
                Tablero tablero = deserializarTablero(estadoTablero);
                Jugador jugador = deserializarJugador(estadoPartida);
                
                GestorTablero gestorTablero = new GestorTablero(tablero);
                GestorJugador gestorJugador = new GestorJugador(jugador, tablero);
                
                pantallaJuegoController juegoController = loader.getController();
                juegoController.initializeController(gestorJugador, gestorTablero, jugador, tablero);
                juegoController.setConnection(con);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Partida cargada");
            } else {
                showAlert("Información", "No tienes partidas guardadas");
            }
        } catch (Exception e) {
            showAlert("Error", "No se pudo cargar la partida: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleRankingPlayers(ActionEvent event) {
        try {
            String sql = "SELECT NICKNAME, PARTIDAS_JUGADAS FROM JUGADORES ORDER BY PARTIDAS_JUGADAS DESC";
            ResultSet rs = bbdd.select(con, sql);

            StringBuilder ranking = new StringBuilder("🏆 Ranking de Jugadores 🏆\n\n");
            int posicion = 1;
            
            while (rs.next()) {
                ranking.append(posicion).append(". ")
                       .append(rs.getString("NICKNAME")).append(" - ")
                       .append(rs.getInt("PARTIDAS_JUGADAS")).append(" partidas\n");
                posicion++;
            }
            
            if (posicion == 1) {
                ranking.append("No hay jugadores registrados aún.");
            }
            
            showAlert("Ranking de Jugadores", ranking.toString());
        } catch (SQLException e) {
            showAlert("Error", "No se pudo obtener el ranking: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleQuitGame(ActionEvent event) {
    	System.exit(0);
    }


    // Métodos auxiliares para deserialización...
    private Tablero deserializarTablero(String estado) {
        // Implementar lógica de deserialización
        return new Tablero();
    }
    
    private Jugador deserializarJugador(String estado) {
        // Implementar lógica de deserialización
        return new Jugador(0, jugador.getNombre(), "Azul", new Pinguino(new Inventario(new ArrayList<>())));
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}