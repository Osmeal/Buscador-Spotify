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
		// boton nueva partida
		actionListener_btnBuscar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (vista.getTxtBusqueda().getText().equals("")) {
					vista.getLblError().setText("Necesitas poner texto en el buscador");
				} else {
					vista.getLblError().setText("");
					vista.getLblArtista().setText(vista.getTxtBusqueda().getText());
				}
			}
		};
		vista.getBtnBuscar().addActionListener(actionListener_btnBuscar);

	}
}
