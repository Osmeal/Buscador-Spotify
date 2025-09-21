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
    private static Gson gson = new Gson();

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

    public static RespuestaSpotify buscarArtista(String nombreArtista) {
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
                return gson.fromJson(json, RespuestaSpotify.class);
            } else {
                throw new RuntimeException("Código de respuesta HTTP: " + codigoRespuesta);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error en la búsqueda de artista", e);
        }
    }

    public static void cargarConfiguracion() throws IOException {
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")) {
            propiedades.load(fis);
        }
        propiedades.forEach((clave, valor) -> System.setProperty(clave.toString(), valor.toString()));
    }
}
