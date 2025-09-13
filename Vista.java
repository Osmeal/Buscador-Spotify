package Spotify;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vista {

	private JFrame frame;
	private JTextField txtBusqueda;
	private JButton btnBuscar;
	private JLabel lblArtista;
	private JLabel lblCancion;
	private JLabel lblImagen;
	private JLabel lblError;

	public Vista() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 779, 494);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(201, 399, 180, 20);
		frame.getContentPane().add(txtBusqueda);
		txtBusqueda.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(404, 398, 89, 23);
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
		lblLogo.setBounds(252, 11, 205, 99);
		frame.getContentPane().add(lblLogo);

		lblArtista = new JLabel("Artista");
		lblArtista.setForeground(new Color(255, 255, 255));
		lblArtista.setBounds(252, 319, 46, 14);
		frame.getContentPane().add(lblArtista);

		lblCancion = new JLabel("Cancion");
		lblCancion.setForeground(new Color(255, 255, 255));
		lblCancion.setBounds(252, 298, 46, 14);
		frame.getContentPane().add(lblCancion);

		lblImagen = new JLabel("Imagen");
		lblImagen.setForeground(new Color(255, 255, 255));
		lblImagen.setBounds(252, 199, 46, 14);
		frame.getContentPane().add(lblImagen);

		frame.setVisible(true);
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JTextField getTxtBusqueda() {
		return txtBusqueda;
	}

	public JLabel getLblArtista() {
		return lblArtista;
	}

	public JLabel getLblCancion() {
		return lblCancion;
	}

	public JLabel getLblImagen() {
		return lblImagen;
	}

	public JLabel getLblError() {
		return lblError;
	}
}
