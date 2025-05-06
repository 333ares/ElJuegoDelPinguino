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
        // Logica para guardar la Partida
    }

    public Tablero cargarPartida() {
		return null;
       //Logica para cargar la partida guardada con anterioridad
    }
    }
