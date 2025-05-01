import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMonedaApi {
    private static final String API_KEY = "b432d378d926eabcb5891978";

    public static String obtenerMonedas(String monedaOrigen) throws IOException, InterruptedException {
        String direccion = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
