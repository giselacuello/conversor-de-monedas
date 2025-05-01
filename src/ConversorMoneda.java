public class ConversorMoneda {
    public static double convertirMoneda(double monto, String monedaDestino, ExchangeRateResponse exchangeRateResponse) {
        //obtener la tasa de cambio de la moneda destino
        Double tasaCambio = exchangeRateResponse.conversion_rates().get(monedaDestino);

        if (tasaCambio != null) {
            //Realizar la conversion y retornar el resultado
            return monto * tasaCambio;
        } else {
            //si no se encuentra la tasa de cambio para la moneda destino
            throw new IllegalArgumentException("Moneda destino no encontrada en las tasas de cambio");
        }
    }
}
