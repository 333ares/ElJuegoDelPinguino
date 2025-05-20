package vista;

import controlador.GestorJugador;
import controlador.GestorTablero;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import modelo.Inventario;
import modelo.Item;
import modelo.Jugador;
import modelo.Pinguino;
import modelo.Tablero;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import bbdd.bbdd;

public class pantallaJuegoController implements GestorMensajes {

	private GestorTablero gestorTablero;
	private Jugador jugadorActual;
	private Tablero tableroActual;

	@FXML
	private Button saveGame;

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
	private GridPane tableroGrid;
	@FXML
	private Circle P1;

	private final int COLUMNS = 5;
	private GestorJugador gestorJugador;
	private Connection con;

	/*
	 * Este método se ejecuta automáticamente al cargar la pantalla del juego.
	 * Inicializa la interfaz gráfica con un mensaje inicial que indica que el juego
	 * ha comenzado.
	 * 
	 */
	
	 @Override
	    public void agregarMensaje(String mensaje) {
		  Platform.runLater(() -> {
		        // Solo añade el nuevo mensaje sin borrar los anteriores
		        eventos.setText(eventos.getText() + "\n" + mensaje);
		        
		        // Opcional: puedes limitar a un máximo de líneas si lo prefieres
		        String[] lineas = eventos.getText().split("\n");
		        if (lineas.length > 20) { // Por ejemplo, mantener 20 líneas como máximo
		            StringBuilder nuevoTexto = new StringBuilder();
		            for (int i = lineas.length - 20; i < lineas.length; i++) {
		                nuevoTexto.append(lineas[i]).append("\n");
		            }
		            eventos.setText(nuevoTexto.toString().trim());
		        }
		    });

	    }

	@FXML
	private void initialize() {
		eventos.setText("¡El juego ha comenzado!");
	}
	
	public void setConnection(Connection con) {
        this.con = con;
    }

	// Boton de guardar del juego
	@FXML
	private void handleSaveGame(ActionEvent event) {
	   try {
	        // 1. Serializar el estado del tablero y del jugador (Pasar a String)
	        String estadoTablero = serializarTablero();
	        String estadoJugador = serializarJugador();
	        
	        // 2. Obtener datos del inventario
	        int numDados = jugadorActual.getPinguino().getInv().getCantidad("dado rápido") + 
	                      jugadorActual.getPinguino().getInv().getCantidad("dado lento");
	        int numPeces = jugadorActual.getPinguino().getInv().getCantidad("pez");
	        int numBolasNieve = jugadorActual.getPinguino().getInv().getCantidad("bola de nieve");

	        // 3. Obtener conexión a la BD
	        Connection con = bbdd.conectarBaseDatos();
	        
	        // 4. Guardar partida
	        String sqlPartida = "INSERT INTO PARTIDAS (NICKNAME, FECHA, HORA, ESTADO_TABLERO, ESTADO_PARTIDA) " +
	                           "VALUES ('" + jugadorActual.getNombre() + "', " +
	                           "SYSDATE, " + // Usamos la fecha actual del sistema
	                           "TO_CHAR(SYSTIMESTAMP, 'HH24:MI:SS'), " +
	                           "'" + estadoTablero + "', " +
	                           "'" + estadoJugador + "')";
	        bbdd.insert(con, sqlPartida);

	        // 5. Actualizar inventario del jugador
	        String sqlInventario = "UPDATE JUGADOR SET " +
	                              "NUM_DADOS = " + numDados + ", " +
	                              "NUM_PECES = " + numPeces + ", " +
	                              "NUM_BOLASNIEVE = " + numBolasNieve + " " +
	                              "WHERE NICKNAME = '" + jugadorActual.getNombre() + "'";
	        bbdd.update(con, sqlInventario);

	        eventos.setText("Partida guardada correctamente");
	    } catch (Exception e) {
	        eventos.setText("Error al guardar la partida: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	private String serializarTablero() {
	    // Implementa la serialización del tablero
	    StringBuilder sb = new StringBuilder();
	    sb.append(jugadorActual.getPosicion()).append(";");
	    sb.append(tableroActual.getTurnos()).append(";");
	    
	    // Serializar casillas especiales
	    for (int i = 0; i < tableroActual.getCasillas().length; i++) {
	        String tipo = tableroActual.getCasillaTipo(i);
	        if (!"Normal".equals(tipo)) {
	            sb.append(i).append(":").append(tipo).append(",");
	        }
	    }
	    
	    return sb.toString();
	}

	private String serializarJugador() {
	    // Implementa la serialización del jugador
	    StringBuilder sb = new StringBuilder();
	    sb.append(jugadorActual.getNombre()).append(";");
	    sb.append(jugadorActual.getColor()).append(";");
	    sb.append(jugadorActual.isProtegidoDelOso()).append(";");
	    
	    // Serializar inventario
	    sb.append("INV:");
	    sb.append("dado_rápido:").append(jugadorActual.getPinguino().getInv().getCantidad("dado rápido")).append(",");
	    sb.append("dado_lento:").append(jugadorActual.getPinguino().getInv().getCantidad("dado lento")).append(",");
	    sb.append("bola_nieve:").append(jugadorActual.getPinguino().getInv().getCantidad("bola de nieve")).append(",");
	    sb.append("pez:").append(jugadorActual.getPinguino().getInv().getCantidad("pez"));
	    
	    return sb.toString();
	}

	// Lógica para hacer funcionar el dado
	@FXML
	private void handleDado(ActionEvent event) {
		// Generar núm y mostrarlo
		Random rand = new Random();
		int diceResult = rand.nextInt(6) + 1;
		dadoResultText.setText("Ha salido: " + diceResult);

		// Delegar todo el movimiento al GestorTablero
		gestorTablero.actualizarMovimientoJugador(jugadorActual, diceResult);
		actualizarInterfazJugador(); // Actualizar tablero después del movimiento
	}

	/*
	 * Este método redibuja las casillas del tablero.. Elimina las imágenes
	 * anteriores de las casillas y coloca nuevas imágenes basadas en el tipo de
	 * cada casilla.
	 *
	 * Mejora: ahora se asigna un ID a las imágenes dinámicas ("casilla-img") para
	 * identificarlas claramente y eliminarlas sin errores.
	 */
	private void actualizarTablero() {
		// Eliminar imágenes dinámicas anteriores del tablero
		List<Node> toRemove = new ArrayList<>(); // Node = Elemento dentro de una estructura de datos
		for (Node node : tableroGrid.getChildren()) {
			// Solo eliminar las imágenes que agregamos nosotros (con ID "casilla-img")
			if (node instanceof ImageView && "casilla-img".equals(node.getId())) {
				toRemove.add(node);
			}
		}
		tableroGrid.getChildren().removeAll(toRemove);

		// Volver a agregar las imágenes según el tipo de casilla
		for (int i = 1; i < 49; i++) {
			ImageView imageView = new ImageView();
			imageView.setId("casilla-img"); // Identificador único para control de limpieza futura

			try {
				// Obtener ruta de imagen según tipo de casilla
				String imagePath = obtenerRutaImagenCasilla(i);
				InputStream is = getClass().getResourceAsStream(imagePath);

				// Si no se encuentra, se lanza excepción para usar imagen por defecto
				if (is == null) {
					throw new IOException("Recurso no encontrado: " + imagePath);
				}

				imageView.setImage(new Image(is));

			} catch (Exception e) {
				System.err.println("Error cargando imagen para casilla " + i + ": " + e.getMessage());
				// Imagen por defecto en caso de error
				imageView.setImage(new Image(getClass().getResourceAsStream("/casillanormal.png")));
			}

			// Ajustar tamaño y posición en el grid
			imageView.setFitWidth(40);
			imageView.setFitHeight(40);
			GridPane.setHalignment(imageView, HPos.RIGHT);
			GridPane.setRowIndex(imageView, i / 5);
			GridPane.setColumnIndex(imageView, i % 5);

			// Agregar al GridPane
			tableroGrid.getChildren().add(imageView);
		}
	}

	// Obtener la ruta de las imagenes para el método de actualizarTablero()
	private String obtenerRutaImagenCasilla(int posicion) {
		if (gestorTablero == null || gestorTablero.getTablero() == null) {
			return "/casillanormal.png";
		}

		String tipo = gestorTablero.getTablero().getCasillaTipo(posicion);
		switch (tipo) {
		case "Oso":
			return "/oso.png";
		case "Agujero":
			return "/agujero.png";
		case "Trineo":
			return "/trineo.png";
		case "Evento":
			return "/evento.png";
		default:
			return "/casillanormal.png";
		}
	}

	// Uso de dado rápido
	@FXML
	public void handleRapido() {
		if (jugadorActual.getPinguino().getInv().contieneItem("dado rápido")) { // Si contiene un ítem llamado "dado
																				// rápido"
			jugadorActual.getPinguino().getInv().quitarItem("dado rápido"); // Quitamos ítem
			int movimiento = new Random().nextInt(6) + 5; // Generamos un núm del 5-10
			gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento); // Pasamos el jugador actual y el
																					// movimiento a gestorTablero
			eventos.setText(eventos.getText() + "\nUsaste dado rápido. Avanzas " + movimiento + " casillas."); // Mostramos
																												// mensaje
			actualizarInterfazJugador();
		} else {
			eventos.setText(eventos.getText() + "\nNo tienes dados rápidos disponibles."); // Si no tiene el dado se
																							// muestra
		}
		actualizarContadoresItems(); // Llamamos al método y actualizamos el contador del menú inferior
	}

	// Uso de dado lento
	@FXML
	public void handleLento() {
		if (jugadorActual.getPinguino().getInv().contieneItem("dado lento")) {
			jugadorActual.getPinguino().getInv().quitarItem("dado lento");
			int movimiento = new Random().nextInt(3) + 1;
			gestorTablero.actualizarMovimientoJugador(jugadorActual, movimiento);
			eventos.setText(eventos.getText() + "\nUsaste dado lento. Avanzas " + movimiento + " casillas.");
			actualizarInterfazJugador();
		} else {
			eventos.setText(eventos.getText() + "\nNo tienes dado lento.");
		}
		actualizarContadoresItems();
	}

	// Uso de la bola de nieve (Por ahora no tiene efecto real en el juego)
	@FXML
	public void handleNieve() {
		if (jugadorActual.getPinguino().getInv().contieneItem("bola de nieve")) {
			jugadorActual.getPinguino().getInv().quitarItem("bola de nieve");
			eventos.setText(eventos.getText() + "\nNo hay un segundo jugador para afectar.");

		} else {
			eventos.setText(eventos.getText() + "\nNo tienes bolas de nieve.");
		}
		actualizarContadoresItems();
	}

	// Uso de peces
	@FXML
	public void handlePeces() {
		if (jugadorActual.getPinguino().getInv().contieneItem("pez")) {
			jugadorActual.getPinguino().getInv().quitarItem("pez");
			jugadorActual.setProtegidoDelOso(true); // Pasamos a estar protegidos del oso
			eventos.setText(eventos.getText() + "\nUsaste pez. Estás protegido contra los osos."); // Mostramos mensaje
			actualizarInterfazJugador(); // Actualizamos la interfaz del jugador
		} else {
			eventos.setText(eventos.getText() + "\nNo tienes peces.");
		}
		actualizarContadoresItems();
	}

	// Actualiza la posición del jugador en la interfaz gráfica
	private void actualizarInterfazJugador() {
		Platform.runLater(() -> { // Permite que una tarea se ejecute más tarde en el hilo de la UI
			int posicion = jugadorActual.getPosicion();
			int row = posicion / COLUMNS;
			int col = posicion % COLUMNS;

			GridPane.setRowIndex(P1, row);
			GridPane.setColumnIndex(P1, col);

			actualizarContadoresItems();
		});
	}

	// Actualiza los textos que muestran cuántos ítems tiene el jugador en su
	// inventario.
	private void actualizarContadoresItems() {
		// Actualizar los textos que muestran la cantidad de items
		int peces = jugadorActual.getPinguino().getInv().getCantidad("pez");
		int bolasNieve = jugadorActual.getPinguino().getInv().getCantidad("bola de nieve");
		int dadosRapidos = jugadorActual.getPinguino().getInv().getCantidad("dado rápido");
		int dadosLentos = jugadorActual.getPinguino().getInv().getCantidad("dado lento");

		peces_t.setText("Peces: " + peces);
		nieve_t.setText("Bolas de nieve: " + bolasNieve);
		rapido_t.setText("Dado rápido: " + dadosRapidos);
		lento_t.setText("Dado lento: " + dadosLentos);

	}

	/*
	 * Inicializa el controlador con las dependencias necesarias.
	 * 
	 * Valida parámetros y configura el estado inicial del juego.
	 */
	public void initializeController(GestorJugador gestorJugador, GestorTablero gestorTablero, Jugador jugadorActual,
			Tablero tablero) {
		this.gestorJugador = gestorJugador;
		this.gestorTablero = gestorTablero;
		this.jugadorActual = jugadorActual;
		this.tableroActual = tablero;

		 // Configurar el gestor de mensajes
        jugadorActual.setGestorMensajes(this);
		
		// Inicializar inventario con items básicos si está vacío
		if (this.jugadorActual.getPinguino() == null) {
			this.jugadorActual.setPinguino(new Pinguino(new Inventario(new ArrayList<>())));
		}

		if (this.jugadorActual.getPinguino().getInv() == null) {
			this.jugadorActual.getPinguino().setInv(new Inventario(new ArrayList<>()));
		}

		// Añadir items iniciales si el inventario está vacío
		if (this.jugadorActual.getPinguino().getInv().getLista().isEmpty()) {
			this.jugadorActual.getPinguino().getInv().añadirItem(new Item("dado rápido", 0));
			this.jugadorActual.getPinguino().getInv().añadirItem(new Item("dado lento", 0));
			this.jugadorActual.getPinguino().getInv().añadirItem(new Item("bola de nieve", 0));
			this.jugadorActual.getPinguino().getInv().añadirItem(new Item("pez", 0));
		}

		// Actualización inicial de la UI
		Platform.runLater(() -> { // Permite que una tarea se ejecute más tarde en el hilo de la UI
			actualizarTablero();
			actualizarInterfazJugador();
		});
	}

}