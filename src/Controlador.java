package Spotify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
	private Modelo modelo;
	private static Vista vista;
	public String usuario;

	private ActionListener actionListener_btnBuscar;

	public Controlador(Modelo modelo, Vista vista) {
		// TODO Auto-generated constructor stub
		this.modelo = modelo;
		this.vista = vista;
		Control();
	}

	public void Control() {
		// boton buscar
		actionListener_btnBuscar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (vista.getTxtBusqueda().getText().equals("")) {
					vista.getLblError().setText("Necesitas poner texto en el buscador");
				} else {
					vista.getLblError().setText("");
					
					String artista = vista.getTxtBusqueda().getText();
					String json = Modelo.buscarArtista(artista);
					String urlImagen = Modelo.obtenerImagen(json);
					String genero = Modelo.obtenerGenero(json);
					String seguidores = Modelo.obtenerSeguidores(json);
					
					vista.getLblArtista().setText(artista);
					vista.mostrarImagenArtista(urlImagen);
					vista.getLblCancion().setText(seguidores);

				}
			}
		};
		vista.getBtnBuscar().addActionListener(actionListener_btnBuscar);

	}
}
