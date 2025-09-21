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
		actionListener_btnBuscar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTxtBusqueda().getText().equals("")) {
					vista.getLblError().setText("Necesitas poner texto en el buscador");
				} else {
					vista.getLblError().setText("");
					String artistaBuscado = vista.getTxtBusqueda().getText();
					RespuestaSpotify response = Modelo.buscarArtista(artistaBuscado);

					if (response != null && !response.artists.items.isEmpty()) {
						RespuestaSpotify.Artist artista = response.artists.items.get(0);

						vista.getLblArtista().setText(artista.name);

						if (!artista.images.isEmpty()) {
							vista.mostrarImagenArtista(artista.images.get(0).url);
						} else {
							vista.mostrarImagenArtista(null);
						}

						vista.getLblCancion().setText("Seguidores: " + artista.followers.total);

						if (!artista.genres.isEmpty()) {
							vista.getLblCancion().setText("Género: " + artista.genres.get(0));
						} else {
							vista.getLblCancion().setText("Género no disponible");
						}
					} else {
						vista.getLblError().setText("No se encontraron artistas.");
					}
				}
			}
		};

		vista.getBtnBuscar().addActionListener(actionListener_btnBuscar);

	}
}
