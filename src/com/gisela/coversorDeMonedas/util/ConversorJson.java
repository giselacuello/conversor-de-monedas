package com.gisela.coversorDeMonedas.util;

import com.google.gson.Gson;
import com.gisela.coversorDeMonedas.modelo.ExchangeRateResponse;

public class ConversorJson {
    public static ExchangeRateResponse convertirDesdeJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ExchangeRateResponse.class);
    }
}
