package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbdd.bbdd;
import modelo.Casilla;
import modelo.Inventario;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;

public class GestorPartidas {

    String urlBDD;
    String username;
    String password;

    public GestorPartidas(String urlBDD, String username, String password) {
        this.urlBDD = urlBDD;
        this.username = username;
        this.password = password;
    }

    public void guardarPartida(Tablero t) {
        Connection con = bbdd.conectarBaseDatos();
        if (con != null) {
            // Aquí iría la lógica para conectar con la base de datos y guardar el estado
            // del tablero, las posiciones de los jugadores, sus inventarios, etc.
            String sql = "INSERT INTO partida (posicion_jugador1, posicion_jugador2) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, t.getJugadores().get(0).getPosicion());
                pstmt.setInt(2, t.getJugadores().get(1).getPosicion());
                pstmt.executeUpdate();
                System.out.println("Partida guardada con éxito.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }

    public Tablero cargarPartida() {
        Connection con = bbdd.conectarBaseDatos();
        Tablero tableroCargado = new Tablero(new Casilla[50], new ArrayList<>(), 0, null);
        if (con != null) {
            // Aquí iría la lógica para conectar con la base de datos y cargar el estado
            // del tablero, las posiciones de los jugadores, sus inventarios, etc.
            String sql = "SELECT posicion_jugador1, posicion_jugador2 FROM partida";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    int posicion1 = rs.getInt("posicion_jugador1");
                    int posicion2 = rs.getInt("posicion_jugador2");
                    // Crear jugador y configurar su inventario
                    Jugador jugador1 = new Jugador(posicion1, "Cargado", "Cargado", new Pinguino(new Inventario(new ArrayList<>())));
                    Jugador jugador2 = new Jugador(posicion2, "Cargado", "Cargado", new Pinguino(new Inventario(new ArrayList<>())));
                    tableroCargado.getJugadores().add(jugador1);
                    tableroCargado.getJugadores().add(jugador2);
                }
                System.out.println("Cargando partida...");
                System.out.println("Partida cargada con éxito.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
        return tableroCargado;
    }
}