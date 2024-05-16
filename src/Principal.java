import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String apyKey = "68eb80150c1d7d82b5c22e1b";
        Scanner lectura = new Scanner(System.in);
        int menuOpcion = 0;

        List<String> MonedasBase = new ArrayList<>();
        MonedasBase.add("MXN");
        MonedasBase.add("ARS");
        MonedasBase.add("CLP");
        MonedasBase.add("COP");
        MonedasBase.add("BRL");
        MonedasBase.add("USD");
        MonedasBase.add("CAD");
        MonedasBase.add("EUR");
        MonedasBase.add("JPY");
        MonedasBase.add("CRC");

        while (menuOpcion != 9) {
            System.out.println("""

            Conversor de divisas y temperaturas. seleccione una de las opciones
            1 - Divisas     2 - Temperaturas   9 - Salir

            """);
            menuOpcion = lectura.nextInt();

            switch (menuOpcion) {
                case 1:
                    System.out.println("""
                                    1 - MXN (Peso México)       6 - USD (Dólar EEUU)
                                    2 - ARS (Peso Argentina)    7 - CAD (Dólar Canada)
                                    3 - CLP (Peso Chile)        8 - EUR (Euros)
                                    4 - COP (Peso Colombia)     9 - JPY (Yen Japón)
                                    5 - BRL (Real Brasil)      10 - CRC (Colón Costa Rica)"""
                    );

                    System.out.println("indica el número de la moneda de origen: ");
                    int monedaOrigen = lectura.nextInt();

                    System.out.println("Ingresa el monto que quieres convertir: ");
                    double monto = lectura.nextDouble();

                    System.out.println("Indica el número de la moneda para la conversión: ");
                    int monedaFinal = lectura.nextInt();

                    URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"
                            +apyKey
                            +"/pair/"
                            +MonedasBase.get(monedaOrigen - 1)+"/"
                            +MonedasBase.get(monedaFinal - 1)+"/"
                            +monto);

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(direccion)
                            .build();
                    
                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());
                    Divisas divisas = new Gson().fromJson(response.body(), Divisas.class);

                    System.out.println("\nSe convertirá el monto de "
                            + monto + " "
                            + divisas.base_code() + " a su equivalencia en "
                            + divisas.target_code()
                            + "\nusando un valor de divisa estimado en "
                            + divisas.conversion_rate());

                    System.out.println("El resultado de la conversión es: " + divisas.conversion_result() + "\n");
                    break;

                case 2:
                    System.out.println(
                    """
                    1. Celsius a Fahrenheit
                    2. Celsius a Kelvin
                    3. Fahrenheit a Celsius
                    4. Fahrenheit a Kelvin
                    5. Kelvin a Celsius
                    6. Kelvin a Fahrenheit
                    9. Salir
                    
                    Elije una opción:
                    """);

                    int opcionTemperatura = lectura.nextInt();
                    double temperatura1 = 0;
                    double temperatura2 = 0;

                    switch (opcionTemperatura) {
                        case 1:
                            System.out.println("Ingresa los grados Celsius a convertir :");
                            temperatura1 = lectura.nextDouble();
                            temperatura2 = ((temperatura1 * 9 / 5) + 32);
                            System.out.println("La equivalencia en grados Farenheit es: " + temperatura2);
                            System.out.println("\n");
                            break;
                        case 2:
                            System.out.println("Ingresa los grados Celsius a convertir :");
                            temperatura1 = lectura.nextDouble();
                            temperatura2 = (temperatura1 + 273.15);
                            System.out.println("La equivalencia en grados Kelvin es: " + temperatura2);
                            System.out.println("\n");

                            break;
                        case 3:
                            System.out.println("Ingresa los grados Farenheit a convertir :");
                            temperatura1 = lectura.nextDouble();
                            temperatura2 = ((temperatura1 - 32) * 5 / 9);
                            System.out.println("La equivalencia en grados Celsius es: " + temperatura2);
                            System.out.println("\n");
                            break;
                        case 4:
                            System.out.println("Ingresa los grados Farenheit a convertir :");
                            temperatura1 = lectura.nextDouble();
                            temperatura2 = ((temperatura1 - 32) * 5 / 9 + 273.15);
                            System.out.println("La equivalencia en grados Kelvin es: " + temperatura2);
                            System.out.println("\n");
                            break;
                        case 5:
                            System.out.println("Ingresa los grados Kelvin a convertir :");
                            temperatura1 = lectura.nextDouble();
                            temperatura2 = temperatura1 - 273.15;
                            System.out.println("La equivalencia en grados Celsius es: " + temperatura2);
                            System.out.println("\n");
                            break;
                        case 6:
                            System.out.println("Ingresa los grados Kelvin a convertir :");
                            temperatura1 = lectura.nextDouble();
                            temperatura2 = ((temperatura1 - 273.15) * 9 / 5 + 32);
                            System.out.println("La equivalencia en grados Celsius es: " + temperatura2);
                            System.out.println("\n");
                            break;
                    }
                case 9:
                    System.out.printf("Hasta luego.\n");
                    break;
                default:
                    System.out.println("Opción invalida.\n");

            }

        }


    }

}

