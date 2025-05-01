import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ConsultaMonedaApi {
    private static final String API_KEY = cargarApiKey();

    private static String cargarApiKey() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            return props.getProperty("API_KEY");
        } catch (IOException e) {
            System.out.println("No se pudo cargar la API key: " + e.getMessage());
            return null;
        }
    }

    public static String obtenerMonedas(String monedaOrigen) throws IOException, InterruptedException {
        if(API_KEY == null) {
            throw new IllegalStateException("La API key no esta disponible");
        }

        String direccion = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
