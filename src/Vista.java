package Spotify;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.net.URL;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.Icon;

import javafx.embed.swing.JFXPanel; // puente entre Swing y JavaFX
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Vista {

	private JFrame frame;
	private JTextField txtArtista;
	private JTextField txtCancion;
	private JButton btnBuscarArtista;
	private JButton btnBuscarCancion;
	private JLabel lblGenero;
	private JLabel lblGrupo;
	private JLabel lblNombreArtista;
	private JLabel lblNombreCancion;
	private JLabel lblImagenArtista;
	private JLabel lblImagenCancion;
	private JLabel lblSeguidores;
	private JLabel lblErrorArtista;
	private JLabel lblErrorCancion;

	private JFXPanel jfxPanel;

	public Vista() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 779, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image imagen = ImageIO.read(Vista.class.getResourceAsStream("/Spotify/Imagenes/Spotify.png"));
		Image imagenEscalada = imagen.getScaledInstance(90, 65, Image.SCALE_SMOOTH);
		ImageIcon fondo = new ImageIcon(imagenEscalada);
		frame.getContentPane().setLayout(null);

		UIManager.put("TabbedPane.selected", Color.BLACK);
		UIManager.put("TabbedPane.contentAreaColor", Color.BLACK);
		UIManager.put("TabbedPane.foreground", Color.WHITE);
		UIManager.put("TabbedPane.focus", Color.BLACK);
		UIManager.put("TabbedPane.selectedForeground", new Color(30, 215, 96));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 0, 763, 455);
		frame.getContentPane().add(tabbedPane);
		tabbedPane.setBorder(null);
		tabbedPane.setFocusable(false);

		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setOpaque(true);

		SwingUtilities.updateComponentTreeUI(tabbedPane);

		JPanel panelCancion = new JPanel();
		panelCancion.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Cancion", null, panelCancion, null);
		panelCancion.setLayout(null);

		JLabel lblLogo_1 = new JLabel(fondo);
		lblLogo_1.setBounds(0, 0, 67, 73);
		panelCancion.add(lblLogo_1);

		lblNombreCancion = new JLabel("Nombre");
		lblNombreCancion.setForeground(Color.WHITE);
		lblNombreCancion.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNombreCancion.setBounds(276, 61, 205, 14);
		panelCancion.add(lblNombreCancion);

		txtCancion = new JTextField();
		txtCancion.setColumns(10);
		txtCancion.setBounds(166, 391, 180, 20);
		panelCancion.add(txtCancion);

		btnBuscarCancion = new JButton("Buscar");
		btnBuscarCancion.setForeground(Color.BLACK);
		btnBuscarCancion.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
		btnBuscarCancion.setBackground(new Color(30, 215, 96));
		btnBuscarCancion.setBounds(369, 390, 89, 23);
		btnBuscarCancion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelCancion.add(btnBuscarCancion);

		lblImagenCancion = new JLabel("");
		lblImagenCancion.setForeground(Color.WHITE);
		lblImagenCancion.setBounds(166, 61, 100, 89);
		panelCancion.add(lblImagenCancion);

		lblErrorCancion = new JLabel("");
		lblErrorCancion.setForeground(new Color(204, 0, 0));
		lblErrorCancion.setBounds(166, 376, 292, 14);
		panelCancion.add(lblErrorCancion);

		lblGrupo = new JLabel("Género");
		lblGrupo.setForeground(Color.WHITE);
		lblGrupo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblGrupo.setBounds(276, 86, 205, 14);
		panelCancion.add(lblGrupo);

		JPanel panelArtista = new JPanel();
		panelArtista.setBackground(new Color(0, 0, 0));
		tabbedPane.addTab("Artista", null, panelArtista, null);
		panelArtista.setLayout(null);

		txtArtista = new JTextField();
		txtArtista.setBounds(211, 392, 180, 20);
		panelArtista.add(txtArtista);
		txtArtista.setColumns(10);

		btnBuscarArtista = new JButton("Buscar");
		btnBuscarArtista.setBounds(414, 391, 89, 23);
		panelArtista.add(btnBuscarArtista);
		btnBuscarArtista.setBackground(new Color(30, 215, 96));
		btnBuscarArtista.setForeground(Color.BLACK);
		btnBuscarArtista.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
		btnBuscarArtista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblGenero = new JLabel("Género");
		lblGenero.setBounds(211, 320, 205, 14);
		panelArtista.add(lblGenero);
		lblGenero.setFont(new Font("Arial", Font.PLAIN, 12));
		lblGenero.setForeground(new Color(255, 255, 255));

		lblNombreArtista = new JLabel("Nombre");
		lblNombreArtista.setBounds(211, 284, 205, 14);
		panelArtista.add(lblNombreArtista);
		lblNombreArtista.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNombreArtista.setForeground(new Color(255, 255, 255));

		lblImagenArtista = new JLabel("");
		lblImagenArtista.setBounds(211, 62, 205, 191);
		panelArtista.add(lblImagenArtista);
		lblImagenArtista.setForeground(new Color(255, 255, 255));

		lblSeguidores = new JLabel("Seguidores");
		lblSeguidores.setBounds(211, 352, 205, 14);
		panelArtista.add(lblSeguidores);
		lblSeguidores.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSeguidores.setForeground(Color.WHITE);

		JLabel lblLogo = new JLabel(fondo);
		lblLogo.setBounds(0, 0, 67, 73);
		panelArtista.add(lblLogo);

		lblErrorArtista = new JLabel("");
		lblErrorArtista.setForeground(new Color(204, 0, 0));
		lblErrorArtista.setBounds(211, 374, 292, 14);
		panelArtista.add(lblErrorArtista);

		// Reproductor (iframe)
		jfxPanel = new JFXPanel();
		jfxPanel.setBounds(166, 170, 400, 150);
		panelCancion.add(jfxPanel);

		frame.setVisible(true);
	}

	public JButton getBtnBuscarArtista() {
		return btnBuscarArtista;
	}

	public JButton getBtnBuscarCancion() {
		return btnBuscarCancion;
	}

	public JTextField getTxtArtista() {
		return txtArtista;
	}

	public JTextField getTxtCancion() {
		return txtCancion;
	}

	public JLabel getLblGenero() {
		return lblGenero;
	}

	public JLabel getLblGrupo() {
		return lblGrupo;
	}

	public JLabel getLblNombreArtista() {
		return lblNombreArtista;
	}

	public JLabel getLblNombreCancion() {
		return lblNombreCancion;
	}

	public JLabel getLblSeguidores() {
		return lblSeguidores;
	}

	public JLabel getLblImagenArtista() {
		return lblImagenArtista;
	}

	public JLabel getLblImagenCancion() {
		return lblImagenCancion;
	}

	public JLabel getLblErrorArtista() {
		return lblErrorArtista;
	}

	public JLabel getLblErrorCancion() {
		return lblErrorCancion;
	}

	public void mostrarImagenArtista(String urlImagen) {
		try {
			ImageIcon icono = new ImageIcon(new URL(urlImagen));
			Image imagenEscalada = icono.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
			lblImagenArtista.setIcon(new ImageIcon(imagenEscalada));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarImagenCancion(String urlImagen) {
		try {
			ImageIcon icono = new ImageIcon(new URL(urlImagen));
			Image imagenEscalada = icono.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
			lblImagenCancion.setIcon(new ImageIcon(imagenEscalada));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarReproductor(String trackId) {
		Platform.runLater(() -> {
			WebView webView = new WebView();
			WebEngine engine = webView.getEngine();
			String url = "https://open.spotify.com/embed/track/" + trackId + "?utm_source=generator";
			engine.load(url);
			Scene scene = new Scene(webView, 500, 200);
			jfxPanel.setScene(scene);
		});
	}

}
