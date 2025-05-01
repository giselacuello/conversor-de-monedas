import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void procesarOpcion(int opcion) {
        Scanner teclado = new Scanner(System.in);
        double cantidad = 0.0;
        String monedaOrigen = "";
        String monedaDestino = "";

        switch (opcion) {
            case 1:
                System.out.println("Conversión de Dólar a Peso argentino");
                System.out.println("Ingrese la cantidad en dólares");
                monedaOrigen = "USD";
                monedaDestino = "ARS";
                break;
            case 2:
                System.out.println("Conversión de Peso argentino a Real brasileño");
                System.out.println("Ingrese la cantidad en pesos argentinos");
                monedaOrigen = "ARS";
                monedaDestino = "BRL";

                break;
            case 3:
                System.out.println("Conversión de Dólar a Real brasileño");
                System.out.println("Ingrese la cantidad en dólares");
                monedaOrigen = "USD";
                monedaDestino = "BRL";

                break;
            case 4:
                System.out.println("Conversión de Real brasileño a Dólar");
                System.out.println("Ingrese la cantidad en reales");
                monedaOrigen = "BRL";
                monedaDestino = "USD";
                break;
            case 5:
                System.out.println("Conversión de Dólar a Peso colombiano");
                System.out.println("Ingrese la cantidad en dólares");
                monedaOrigen = "USD";
                monedaDestino = "COP";
                break;
            case 6:
                System.out.println("Conversión de Peso colombiano a Dólar");
                System.out.println("Ingrese la cantidad en pesos colombianos");
                monedaOrigen = "COP";
                monedaDestino = "USD";
                break;
            default:
                System.out.println("Opción inválida. Por favor, elija una opción del 1 al 7.");
                break;
        }
        //para manejar una entrada que no sea un valor numérico
        try {
            cantidad = teclado.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor ingrese un monto válido.");
            teclado.next();
            return;
        }
        try {
            //obtener la respuesta json
            String jsonRespuesta = ConsultaMonedaApi.obtenerMonedas(monedaOrigen);

            //convertir la respuesta JSON a un objeto ExchangeRateResponse usandola clase ConversorJson
            ExchangeRateResponse exchangeRateResponse = ConversorJson.convertirDesdeJson(jsonRespuesta);

            // Validar si la respuesta o las tasas de cambio son nulas
            if (exchangeRateResponse == null || exchangeRateResponse.conversion_rates() == null) {
                System.out.println("Error: No se pudo obtener la información de tasas de cambio.");
                return; //sale del metodo procesaroOpcion
            }

            //Llamar al conversor de monedas con la cantidad y la moneda de destino
            double resultado = ConversorMoneda.convertirMoneda(cantidad, monedaDestino, exchangeRateResponse);

            //mostrar resultado
            System.out.println("El monto convertido es: " + resultado);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error en la consulta " + e.getMessage());
        }
    }
}
