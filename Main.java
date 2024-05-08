import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner inputSeleccion = new Scanner(System.in);
        Scanner inputMonto = new Scanner(System.in);
        int seleccionCategoria = 0;
        double monto = 0;
        String divisa = "";
        Gson gson = new Gson();

        while (true) {
            System.out.println("""
                    --------------------------------------------
                    Selecciona la opcion que deseas hacer:
                    1 - USD -> ARS
                    2 - ARS -> USD
                    3 - USD -> BRL
                    4 - BRL -> USD
                    5 - USD -> COP
                    6 - COP -> USD
                    7 - Salir 
                    --------------------------------------------
                    """);
            seleccionCategoria = inputSeleccion.nextInt();

            if (seleccionCategoria == 1 || seleccionCategoria == 3 || seleccionCategoria == 5) {
                divisa = "USD";
            } else if (seleccionCategoria == 2) {
                divisa = "ARS";
            } else if (seleccionCategoria == 4) {
                divisa = "BRL";
            } else if (seleccionCategoria == 6) {
                divisa = "COP";
            }

            HttpClient client = HttpClient.newHttpClient();
            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/4581bf078b633e4e34941aa3/latest/" + divisa);
            HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

            //prueba
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
                JsonObject monedasObject = jsonObject.getAsJsonObject("conversion_rates");

                if (seleccionCategoria == 1) {
                    System.out.println("Ingresa el monto a cambiar: ");
                    monto = inputMonto.nextDouble();
                    double conversion = monto * monedasObject.get("ARS").getAsDouble();
                    System.out.println("La conversion final seria: " + conversion);
                } else if (seleccionCategoria == 2) {
                    System.out.println("Ingresa el monto a cambiar: ");
                    monto = inputMonto.nextDouble();
                    double conversion = monto * monedasObject.get("USD").getAsDouble();
                    System.out.println("La conversion final seria: " + conversion);
                } else if (seleccionCategoria == 3) {
                    System.out.println("Ingresa el monto a cambiar: ");
                    monto = inputMonto.nextDouble();
                    double conversion = monto * monedasObject.get("BRL").getAsDouble();
                    System.out.println("La conversion final seria: " + conversion);
                } else if (seleccionCategoria == 4) {
                    System.out.println("Ingresa el monto a cambiar: ");
                    monto = inputMonto.nextDouble();
                    double conversion = monto * monedasObject.get("USD").getAsDouble();
                    System.out.println("La conversion final seria: " + conversion);
                } else if (seleccionCategoria == 5) {
                    System.out.println("Ingresa el monto a cambiar: ");
                    monto = inputMonto.nextDouble();
                    double conversion = monto * monedasObject.get("COP").getAsDouble();
                    System.out.println("La conversion final seria: " + conversion);
                } else if (seleccionCategoria == 6) {
                    System.out.println("Ingresa el monto a cambiar: ");
                    monto = inputMonto.nextDouble();
                    double conversion = monto * monedasObject.get("USD").getAsDouble();
                    System.out.println("La conversion final seria: " + conversion);
                } else if (seleccionCategoria == 7) {
                    System.out.println("""
                        Estas saliendo del programa
                        Gracias por usarlo!!
                        """);
                    break;
                } else {
                    System.out.println("Opcion invalida");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
}
