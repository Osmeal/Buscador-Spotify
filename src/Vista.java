package Spotify;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.net.URL;
import java.awt.Font;
import javax.swing.JTabbedPane;

public class Vista {

	private JFrame frame;
	private JTextField txtBusqueda;
	private JButton btnBuscar;
	private JLabel lblgGenero;
	private JLabel lblNombre;
	private JLabel lblImagen;
	private JLabel lblPopularidad;
	private JLabel lblError;

	public Vista() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 779, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(268, 393, 180, 20);
		frame.getContentPane().add(txtBusqueda);
		txtBusqueda.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(0, 153, 0));
		btnBuscar.setBounds(471, 392, 89, 23);
		btnBuscar.setBackground(new Color(0x1DB954)); // color Spotify green (#1DB954)
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		frame.getContentPane().add(btnBuscar);

		lblError = new JLabel("");
		lblError.setForeground(new Color(204, 0, 0));
		lblError.setBounds(201, 374, 292, 14);
		frame.getContentPane().add(lblError);
		/*
		 * Image imagen =
		 * ImageIO.read(Vista.class.getResourceAsStream("/Spotify/Imagenes/Spotify.png")
		 * ); Image imagenEscalada = imagen.getScaledInstance(90, 65,
		 * Image.SCALE_SMOOTH); ImageIcon fondo = new ImageIcon(imagenEscalada);
		 * 
		 * JLabel lblLogo = new JLabel(fondo); lblLogo.setBounds(49, 41, 67, 73);
		 * frame.getContentPane().add(lblLogo);
		 */
		Image imagen = ImageIO.read(Vista.class.getResourceAsStream("/Spotify/Imagenes/SpotifyLogo.png"));
		Image imagenEscalada = imagen.getScaledInstance(170, 145, Image.SCALE_SMOOTH);
		ImageIcon fondo = new ImageIcon(imagenEscalada);

		JLabel lblLogo = new JLabel(fondo);
		lblLogo.setBounds(10, 11, 205, 99);
		frame.getContentPane().add(lblLogo);

		lblgGenero = new JLabel("Género");
		lblgGenero.setFont(new Font("Arial", Font.PLAIN, 12));
		lblgGenero.setForeground(new Color(255, 255, 255));
		lblgGenero.setBounds(268, 321, 205, 14);
		frame.getContentPane().add(lblgGenero);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setBounds(268, 285, 205, 14);
		frame.getContentPane().add(lblNombre);

		lblImagen = new JLabel("");
		lblImagen.setForeground(new Color(255, 255, 255));
		lblImagen.setBounds(268, 63, 205, 191);
		frame.getContentPane().add(lblImagen);
		
		lblPopularidad = new JLabel("Seguidores");
		lblPopularidad.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPopularidad.setForeground(Color.WHITE);
		lblPopularidad.setBounds(268, 353, 205, 14);
		frame.getContentPane().add(lblPopularidad);

		frame.setVisible(true);
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JTextField getTxtBusqueda() {
		return txtBusqueda;
	}

	public JLabel getLblGenero() {
		return lblgGenero;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblPopularidad() {
		return lblPopularidad;
	}
	
	public JLabel getLblImagen() {
		return lblImagen;
	}

	public JLabel getLblError() {
		return lblError;
	}
	
	public void mostrarImagenArtista(String urlImagen) {
	    try {
	        ImageIcon icono = new ImageIcon(new URL(urlImagen));
	        Image imagenEscalada = icono.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
	        lblImagen.setIcon(new ImageIcon(imagenEscalada));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
