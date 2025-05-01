# Conversor de Monedas

Este es un proyecto en Java que permite convertir montos entre diferentes monedas.  
El usuario puede elegir una conversión entre varias opciones (como de dólar a peso argentino, o de real brasileño a dólar), ingresar el monto y obtener el resultado actualizado según tasas de cambio en tiempo real.

## Funcionalidad

Desde un menú interactivo, el usuario puede:

- Seleccionar el tipo de conversión
- Ingresar el monto a convertir
- Ver el resultado calculado con datos actuales

Las tasas de cambio se obtienen desde la API pública [ExchangeRate-API](https://www.exchangerate-api.com/).

## Tecnologías utilizadas

- **Java JDK 17**
- **Gson** (para parsear JSON)
- **ExchangeRate-API** (para obtener las tasas de cambio)

## Requisitos

- Tener instalado [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Tener [IntelliJ IDEA](https://www.jetbrains.com/idea/) o cualquier otro IDE Java
- Agregar la biblioteca **Gson** (puede instalarse desde Maven Central o agregando manualmente el `.jar`)

## Ejecución

1. Clonar o descargar el repositorio
2. Abrir el proyecto en IntelliJ u otro IDE compatible
3. Asegurarse de que la librería Gson esté incluida en las dependencias
4. Ejecutar la clase `Principal.java`

## Ejemplo de uso

```plaintext
**************************************
Sea bienvenido/a al conversor de Moneda

1) Dólar =>> Peso argentino
2) Peso argentino =>> Real brasileño
3) Dólar =>> Real brasileño
4) Real brasileño =>> Dólar
5) Dólar =>> Peso colombiano
6) Peso colombiano =>> Dólar
7) Salir
Elija una opción válida:
**************************************

>> 1
Conversión de Dólar a Peso argentino  
Ingrese la cantidad en dólares:  
>> 100  
El monto convertido es: 87800.0
