package Spotify;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

import com.google.gson.Gson;

public class Modelo {
	private static String tokenDeAcceso;

	Modelo() throws IOException {
		cargarConfiguracion();
		obtenerToken();
	}

	public static void obtenerToken() {
		try {

			URL url = new URL("https://accounts.spotify.com/api/token");
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

			conexion.setRequestMethod("POST");
			conexion.setDoOutput(true);

			String idCliente = System.getProperty("SPOTIFY_CLIENT_ID");
			String secretoCliente = System.getProperty("SPOTIFY_CLIENT_SECRET");
			String autenticacion = idCliente + ":" + secretoCliente;
			String autenticacionCodificada = Base64.getEncoder().encodeToString(autenticacion.getBytes());

			conexion.setRequestProperty("Authorization", "Basic " + autenticacionCodificada);
			conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			String cuerpo = "grant_type=client_credentials";
			conexion.getOutputStream().write(cuerpo.getBytes());

			int codigoRespuesta = conexion.getResponseCode();
			if (codigoRespuesta == 200) {
				Scanner scanner = new Scanner(conexion.getInputStream());
				StringBuilder respuesta = new StringBuilder();
				while (scanner.hasNext()) {
					respuesta.append(scanner.nextLine());
				}
				scanner.close();

				String json = respuesta.toString();
				int inicio = json.indexOf("\"access_token\":\"") + 16;
				int fin = json.indexOf("\"", inicio);
				tokenDeAcceso = json.substring(inicio, fin);
			} else {
				throw new RuntimeException("Codigo de Respuesta HTTP: " + codigoRespuesta);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Error en la conexión con Spotify", e);
		}
	}

	public static String buscarArtista(String nombreArtista) {
		try {
			String query = nombreArtista.replace(" ", "%20");
			URL url = new URL("https://api.spotify.com/v1/search?q=" + query + "&type=artist&limit=1");

			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");
			conexion.setRequestProperty("Authorization", "Bearer " + tokenDeAcceso);

			int codigoRespuesta = conexion.getResponseCode();
			if (codigoRespuesta == 200) {
				Scanner scanner = new Scanner(conexion.getInputStream());
				StringBuilder respuesta = new StringBuilder();
				while (scanner.hasNext()) {
					respuesta.append(scanner.nextLine());
				}
				scanner.close();

				String json = respuesta.toString();
				System.out.println(json);
				return json;

			} else {
				throw new RuntimeException("Código de respuesta HTTP: " + codigoRespuesta);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Error en la búsqueda de artista", e);
		}
	}

	public static String obtenerImagen(String json) {
		int indexImagen = json.indexOf("\"url\":\"") + 7;
		int finImagen = json.indexOf("\"", indexImagen);
		String urlImagen = json.substring(indexImagen, finImagen);

		return urlImagen;
	}

	public static String obtenerGenero(String json) {
		int indexGenres = json.indexOf("\"genres\":") + 9;
		int startArray = json.indexOf("[", indexGenres) + 1;
		int endArray = json.indexOf("]", startArray);

		String genresString = json.substring(startArray, endArray).trim();
		if (!genresString.isEmpty()) {
			int firstComma = genresString.indexOf(",");
			if (firstComma != -1) {
				return genresString.substring(1, firstComma - 1);
			} else {
				return genresString.substring(1, genresString.length() - 1);
			}
		} else {
			return "Género no disponible";
		}
	}

	public static String obtenerSeguidores(String json) {
		int indexFollowers = json.indexOf("\"followers\":") + 12;
		int indexTotal = json.indexOf("\"total\":", indexFollowers) + 8; 
		int finSeguidores = json.indexOf("}", indexTotal);

		String seguidores = json.substring(indexTotal, finSeguidores).trim();

		return seguidores;
	}

	public static void cargarConfiguracion() throws IOException {
		Properties propiedades = new Properties();
		try (FileInputStream fis = new FileInputStream(".env")) {
			propiedades.load(fis);
		}
		propiedades.forEach((clave, valor) -> System.setProperty(clave.toString(), valor.toString()));
	}
}
