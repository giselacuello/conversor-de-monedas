import com.google.gson.Gson;

public class ConversorJson {
    public static ExchangeRateResponse convertirDesdeJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ExchangeRateResponse.class);
    }
}
