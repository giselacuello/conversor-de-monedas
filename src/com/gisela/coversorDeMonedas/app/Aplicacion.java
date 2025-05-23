package com.gisela.coversorDeMonedas.app;

import com.gisela.coversorDeMonedas.vista.Menu;

import java.util.Scanner;

public class Aplicacion {

    Scanner teclado = new Scanner(System.in);
    Menu menu = new Menu(teclado);

    public void ejecutar() {
        boolean salir = false;
        int opcion;

        while (!salir) {
            System.out.println("""
                    **************************************
                    Sea bienvenido/a al conversor de Moneda
                    
                    1) Dólar =>> Peso argentino
                    2) Peso argentino =>> Real brasileño
                    3) Dólar =>> Real brasileño
                    4) Real brasileño =>> Dólar
                    5) Dólar =>> Peso colombiano
                    6) Peso colombiano =>> Dólar
                    7) Conversion libre
                    8) Salir
                    Elija una opción válida:
                    **************************************
                    """);

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                if (opcion == 8) {
                    System.out.println("Gracias por usar el conversor");
                    salir = true;
                } else if (opcion >= 1 && opcion <= 7) {
                    menu.procesarOpcion(opcion);
                } else {
                    System.out.println("Opción inválida. Por favor, elija una opcion del 1 al 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            }
        }
    }
}
