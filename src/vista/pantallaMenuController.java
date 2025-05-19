package vista;

import bbdd.bbdd;
import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Inventario;
import modelo.Jugador;
import modelo.Tablero;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class pantallaMenuController {

    /*private Connection con;
    private Jugador jugadorActual;
    private Tablero tableroActual;

    public void setJugador(Jugador jugador) {
        this.jugadorActual = jugador;
    }

    @FXML
    private void initialize() {
        con = bbdd.conectarBaseDatos();
    }

    @FXML
    private void handleNewGame(ActionEvent event) {
        tableroActual = new Tablero();
        GestorTablero gestorTablero = new GestorTablero(tableroActual);
        tableroActual.setGestorTablero(gestorTablero);

        GestorJugador gestorJugador = new GestorJugador(jugadorActual, tableroActual);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
            Parent root = loader.load();

            pantallaJuegoController controller = loader.getController();
            controller.initializeController(gestorJugador, gestorTablero, jugadorActual, tableroActual);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Juego en curso");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLoadGame(ActionEvent event) {
        String sql = "SELECT num_partida, fecha, hora FROM Partidas WHERE estado_partida = 0 AND jugador_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, jugadorActual.getNombre());
            ResultSet rs = ps.executeQuery();

            ArrayList<Integer> partidas = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("num_partida");
                partidas.add(id);
                System.out.println("Partida #" + id + " - " + rs.getDate("fecha") + " - " + rs.getInt("hora") + "h");
            }

            if (!partidas.isEmpty()) {
                int idSeleccionado = partidas.get(0); // Puedes mejorar con selección real
                cargarPartida(idSeleccionado, event);
            } else {
                System.out.println("No hay partidas pendientes.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarPartida(int idPartida, ActionEvent event) {
        tableroActual = new Tablero(); // En producción deberías reconstruir el estado
        GestorTablero gestorTablero = new GestorTablero(tableroActual);
        GestorJugador gestorJugador = new GestorJugador(jugadorActual, tableroActual);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantallaJuego.fxml"));
            Parent root = loader.load();
            pantallaJuegoController controller = loader.getController();
            controller.initializeController(gestorJugador, gestorTablero, jugadorActual, tableroActual);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Reanudando partida");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRankingPlayers(ActionEvent event) {
        String sql = "SELECT nickname, partidas_jugadas FROM jugadores ORDER BY partidas_jugadas DESC";
        try {
            ResultSet rs = bbdd.select(con, sql);
            System.out.println("--- Ranking de jugadores ---");
            while (rs.next()) {
                System.out.println(rs.getString("nickname") + ": " + rs.getInt("partidas_jugadas") + " partidas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveGame() {
        if (jugadorActual != null && tableroActual != null) {
            guardarPartida(jugadorActual, tableroActual, true); // true = nueva partida
        } else {
            System.out.println("No hay partida activa para guardar.");
        }
    }

    private void guardarPartida(Jugador jugador, Tablero tablero, boolean esNuevaPartida) {
        int numPartida = generarIdUnico();
        String estadoTablero = tablero.toString(); // simulado
        int estadoPartida = 0; // 0 = en curso

        Inventario inventario = jugadorActual.getPinguino().getInv();
        int dados = inventario.getCantidad("dado rápido") + inventario.getCantidad("dado lento");
        int peces = inventario.getCantidad("pez");
        int bolas = inventario.getCantidad("bola de nieve");

        java.sql.Date fecha = new java.sql.Date(System.currentTimeMillis());
        int hora = LocalTime.now().getHour();

        try {
            String sqlInsert = "INSERT INTO Partidas (num_partida, fecha, hora, estado_tablero, estado_partida, jugador_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, numPartida);
            ps.setDate(2, fecha);
            ps.setInt(3, hora);
            ps.setString(4, estadoTablero);
            ps.setInt(5, estadoPartida);
            ps.setString(6, jugador.getNombre());
            ps.executeUpdate();

            String sqlInventario = "INSERT INTO Inventario_Jugadores (jugador, num_dados, num_peces, num_bolasNieve) VALUES (?, ?, ?, ?)";
            PreparedStatement psInv = con.prepareStatement(sqlInventario);
            psInv.setString(1, jugador.getNombre());
            psInv.setInt(2, dados);
            psInv.setInt(3, peces);
            psInv.setInt(4, bolas);
            psInv.executeUpdate();

            String sqlContador = "UPDATE Jugadores SET partidas_jugadas = partidas_jugadas + 1 WHERE nickname = ?";
            PreparedStatement psUpdate = con.prepareStatement(sqlContador);
            psUpdate.setString(1, jugador.getNombre());
            psUpdate.executeUpdate();

            System.out.println("Partida guardada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int generarIdUnico() {
        return (int)(Math.random() * 100000);
    }
    
    @FXML
    private void handleQuitGame() {
    	System.exit(0);
    }*/
}