package com.gisela.coversorDeMonedas.vista;

import com.gisela.coversorDeMonedas.modelo.ConversorMoneda;
import com.gisela.coversorDeMonedas.modelo.ExchangeRateResponse;
import com.gisela.coversorDeMonedas.servicio.ConsultaMonedaApi;
import com.gisela.coversorDeMonedas.util.ConversorJson;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Scanner teclado;

    public Menu(Scanner teclado) {
        this.teclado = teclado;
    }

    public void procesarOpcion(int opcion) {
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
            case 7:
                System.out.println("Conversión libre");
                monedaOrigen = "USD";
                try {
                    ExchangeRateResponse exchangeRateResponse = obtenerTasas(monedaOrigen);
                    //mostrar monedas disponibles
                    Map<String, Double> tasas = exchangeRateResponse.conversion_rates();
                    System.out.println("Monedas disponibles");
                    for (String monedas : tasas.keySet()) {
                        System.out.println(monedas);
                    }
                    System.out.println();

                    //pedir moneda origen
                    System.out.println("Ingrese la moneda de origen ");
                    monedaOrigen = teclado.nextLine().toUpperCase();

                    //pedir moneda destino
                    System.out.println("Ingrese la moneda a la que desea realizar la conversión");
                    monedaDestino = teclado.nextLine().toUpperCase();

                } catch (IOException | InterruptedException e) {
                    System.out.println("Error al obtener monedas " + e.getMessage());
                }
                System.out.println("Elija la moneda de origen de la siguiente lista");

            default:
                System.out.println("Opción inválida. Por favor, elija una opción del 1 al 8.");
                break;
        }
        //para manejar una entrada que no sea un valor numérico
        try {
            cantidad = teclado.nextDouble();
            teclado.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor ingrese un monto válido.");
            teclado.nextLine();
            return;
        }

        try {
            ExchangeRateResponse exchangeRateResponse = obtenerTasas(monedaOrigen);
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
    private static ExchangeRateResponse obtenerTasas(String moneda) throws IOException, InterruptedException {
        //obtener la respuesta json
        String jsonRespuesta = ConsultaMonedaApi.obtenerMonedas(moneda);

        //retorna conversión de la respuesta JSON a un objeto
        return ConversorJson.convertirDesdeJson(jsonRespuesta);

    }
}
