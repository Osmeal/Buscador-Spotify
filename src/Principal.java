package Spotify;

import java.io.IOException;


public class Principal {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			try {
				Modelo modelo = new Modelo();
				Vista vista = new Vista();
				Controlador Controlador = new Controlador(modelo, vista);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}