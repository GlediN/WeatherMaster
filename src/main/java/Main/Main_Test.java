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

public class Main_Test {
    public static void main(String[] args){
        // Scanner qe te mari qytetin qe do perdoruesi
        Scanner scanner = new Scanner(System.in);

        // Formatues per Date class qe ta kthej ne vit muaj etc
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH z");

        // Merr oren locale e pc
        Date date = new Date(System.currentTimeMillis());

        // Per te mos ta shkruajtur disa her ne tregimin e te dhenave te motit
        String newLine = "\n";

        // List of default city names
        List<String> defaultCityNames = new ArrayList<>();
        defaultCityNames.add("Tirana");
        defaultCityNames.add("Durres");
        defaultCityNames.add("Elbasan");

        try {

            // Mesazhi hyres
            System.out.println("Mirserdhet ne WeatherMaster");
            System.out.println("Moti per qytetet kryesore jan:");

            // Duke perdor for loop te mar nga chatgpt hahaha per te dhen te dhenat e default city
            for (String cityName : defaultCityNames) {

                // Url per api
                URL url_Weather = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=30d43c790a5ff92c061bdaf9c866a004");

                // Me HttpURLConnection simulon request si browser
                HttpURLConnection connection = (HttpURLConnection) url_Weather.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Merr HTTP response code per te par nese ka error
                int responseCode = connection.getResponseCode();

                //Nese nuk kthen responseCode 200 dmth error
                if (responseCode != 200) {
                    throw new RuntimeException("HttpResponseCode:" + responseCode);
                } else {

                    // Merr response e requestit dhe e kthen ne string duke perdor scanner
                    StringBuilder informationString = new StringBuilder();
                    scanner = new Scanner(url_Weather.openStream());

                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                    //Kur nuk ka me text mbyll scannerin
                    scanner.close();

                    // E kthen ne JSON object
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(String.valueOf(informationString));

                    // Nese nuk deklaron attributes te jep te gjitha vlerat kurse kshu mer cilat te duash
                    double temp = (double) ((JSONObject) json.get("main")).get("temp");
                    double tempMin = (double) ((JSONObject) json.get("main")).get("temp_min");
                    double tempMax = (double) ((JSONObject) json.get("main")).get("temp_max");
                    double wind = (double) ((JSONObject) json.get("wind")).get("speed");

                    // Display weather information

                    System.out.println(cityName + " ne daten " + date + " esht:" + newLine
                            + "Temperatura minimale:" + tempMin + "°C " + newLine + "Temperatura aktuale" + temp + "°C "
                            + newLine + "Temperatura maximale" + tempMax + "°C " + newLine);
                }
            }
            String user_City;
            do {
                System.out.println("Vendos emrin e qytetit qe do te shikosh me shum informacion ose shkruaj Exit per te dal nga programi: ");
                user_City = scanner.nextLine();
                URL user_City_Url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + user_City +
                        "&units=metric&appid=30d43c790a5ff92c061bdaf9c866a004");

                HttpURLConnection connection = (HttpURLConnection) user_City_Url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode != 200) {
                    throw new RuntimeException("HttpResponseCode:" + responseCode);
                } else {

                    // Merr response e requestit dhe e kthen ne string duke perdor scanner
                    StringBuilder informationString = new StringBuilder();
                    scanner = new Scanner(user_City_Url.openStream());

                    while (scanner.hasNext()) {
                        informationString.append(scanner.nextLine());
                    }
                    //Kur nuk ka me text mbyll scannerin
                    scanner.close();

                    // E kthen ne JSON object
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(String.valueOf(informationString));

                    // Nese nuk deklaron attributes te jep te gjitha vlerat kurse kshu mer cilat te duash
                    double temp = (double) ((JSONObject) json.get("main")).get("temp");
                    double tempMin = (double) ((JSONObject) json.get("main")).get("temp_min");
                    double tempMax = (double) ((JSONObject) json.get("main")).get("temp_max");
                    double wind = (double) ((JSONObject) json.get("wind")).get("speed");

                    // Display weather information

                    System.out.println(user_City + " ne daten " + date + " esht:" + newLine
                            + "Temperatura minimale:" + tempMin + "°C " + newLine + "Temperatura aktuale" + temp + "°C "
                            + newLine + "Temperatura maximale" + tempMax + "°C " + newLine + newLine + "Shpejtesia eres" + wind);


                }
            }while (!user_City.equals("Exit")) ;
            System.out.println("Exiting the program...");
            scanner.close();
        } catch (Exception exception){

        }
    }
}

