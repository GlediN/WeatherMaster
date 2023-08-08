package Main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        // Dy scanner te ndryshem sepse nese ishte vetem nji
        // programi mbyllej automatikisht
        Scanner scanner = new Scanner(System.in);
        Scanner scanner_User=new Scanner(System.in);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH z");
        Date date = new Date(System.currentTimeMillis());
        String newLine = "\n";
        List<String> defaultCityNames = new ArrayList<>();
        defaultCityNames.add("Tirana");
        defaultCityNames.add("Durres");
        defaultCityNames.add("Elbasan");

        try {
            System.out.println("Mirserdhet ne WeatherMaster");
            System.out.println("Moti per qytetet kryesore jan:");

            for (String cityName : defaultCityNames) {
                URL url_Weather = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=30d43c790a5ff92c061bdaf9c866a004");
                HttpURLConnection connection = (HttpURLConnection) url_Weather.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode != 200) {
                    throw new RuntimeException("HttpResponseCode:" + responseCode);
                } else {
                    StringBuilder informationString = new StringBuilder();
                    scanner = new Scanner(url_Weather.openStream());

                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                    scanner.close();

                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(String.valueOf(informationString));

                    double temp = (double) ((JSONObject) json.get("main")).get("temp");
                    double tempMin = (double) ((JSONObject) json.get("main")).get("temp_min");
                    double tempMax = (double) ((JSONObject) json.get("main")).get("temp_max");
                    double wind = (double) ((JSONObject) json.get("wind")).get("speed");

                    System.out.println(cityName + " ne daten " + date + " esht:" + newLine
                            + "Temperatura aktuale" + temp + "°C "
                            + newLine);
                }
            }
            String user_City;
            do {
                System.out.println("Vendos emrin e qytetit qe do te shikosh me shum informacion ose shkruaj Exit per te dal nga programi: ");
                user_City = scanner_User.nextLine();
                if (user_City.equals("Exit")) {
                    break;
                }
                URL user_City_Url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + user_City +
                        "&units=metric&appid=30d43c790a5ff92c061bdaf9c866a004");

                HttpURLConnection connection = (HttpURLConnection) user_City_Url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode != 200) {
                    throw new RuntimeException("HttpResponseCode:" + responseCode);
                } else {
                    StringBuilder informationString = new StringBuilder();
                    // Reusing the same scanner for reading the user's input stream
                    scanner = new Scanner(user_City_Url.openStream());

                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                    //Mbyll scannerin e informationString
                    scanner.close();

                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(String.valueOf(informationString));

                    double temp = (double) ((JSONObject) json.get("main")).get("temp");
                    double tempMin = (double) ((JSONObject) json.get("main")).get("temp_min");
                    double tempMax = (double) ((JSONObject) json.get("main")).get("temp_max");
                    double wind = (double) ((JSONObject) json.get("wind")).get("speed");

                    System.out.println(user_City + " ne daten " + date + " esht:" + newLine
                            + "Temperatura minimale:" + tempMin + "°C " + newLine + "Temperatura aktuale" + temp + "°C "
                            + newLine + "Temperatura maximale" + tempMax + "°C " +newLine + "Shpejtesia eres " + wind+"km/h"+newLine);

                }
            } while (!user_City.equalsIgnoreCase("Exit"));
            System.out.println("Exiting the program...");
        } catch (Exception exception){

            exception.printStackTrace();
        } finally {
            scanner.close();
            scanner_User.close();// mbyll te dy scanner
        }
    }
}


