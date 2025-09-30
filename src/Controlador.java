package Spotify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
	private Modelo modelo;
	private static Vista vista;
	public String usuario;

	private ActionListener actionListener_btnBuscarArtista, actionListener_btnBuscarCancion;

	public Controlador(Modelo modelo, Vista vista) {
		// TODO Auto-generated constructor stub
		this.modelo = modelo;
		this.vista = vista;
		Control();
	}

	public void Control() {
		// Artista
		actionListener_btnBuscarArtista = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTxtArtista().getText().equals("")) {
					vista.getLblErrorArtista().setText("Necesitas poner texto en el buscador");
				} else {
					vista.getLblErrorArtista().setText("");
					String artistaBuscado = vista.getTxtArtista().getText();
					RespuestaSpotify response = Modelo.buscarArtista(artistaBuscado);

					if (response != null && !response.artists.items.isEmpty()) {
						RespuestaSpotify.Artist artista = response.artists.items.get(0);

						vista.getLblNombreArtista().setText(artista.name);

						if (!artista.images.isEmpty()) {
							vista.mostrarImagenArtista(artista.images.get(0).url);
						} else {
							vista.mostrarImagenArtista(null);
						}

						vista.getLblSeguidores().setText("Seguidores: " + artista.followers.total);

						if (!artista.genres.isEmpty()) {
							vista.getLblGenero().setText("Género: " + artista.genres.get(0));
						} else {
							vista.getLblGenero().setText("Género no disponible");
						}
					} else {
						vista.getLblErrorArtista().setText("No se encontraron artistas.");
					}
				}
			}
		};

		vista.getBtnBuscarArtista().addActionListener(actionListener_btnBuscarArtista);

		// Cancion
		actionListener_btnBuscarCancion = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTxtCancion().getText().equals("")) {
					vista.getLblErrorCancion().setText("Necesitas poner texto en el buscador");
				} else {
					vista.getLblErrorCancion().setText("");
					String cancionBuscada = vista.getTxtCancion().getText();
					RespuestaSpotify response = Modelo.buscarCancion(cancionBuscada);

					if (response != null && response.tracks != null && !response.tracks.items.isEmpty()) {
						RespuestaSpotify.Track track = response.tracks.items.get(0);

						vista.getLblNombreCancion().setText(track.name);
						vista.getLblGrupo().setText(track.artists.get(0).name);

						if (track.album != null && track.album.images != null && !track.album.images.isEmpty()) {
							vista.mostrarImagenCancion(track.album.images.get(0).url);
						} else {
							vista.mostrarImagenCancion(null);
						}
					} else {
						vista.getLblErrorCancion().setText("No se encontraron canciones.");
					}

				}
			}
		};
		vista.getBtnBuscarCancion().addActionListener(actionListener_btnBuscarCancion);
	}
}
