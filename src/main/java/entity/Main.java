package entity;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        City city = new City("London");
        try {
            //Linku i api
            URL url_Weather = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city.getCity_name()
                    + "&units=metric&appid=30d43c790a5ff92c061bdaf9c866a004");

            HttpURLConnection connection = (HttpURLConnection) url_Weather.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //Shofim nese request esht ne rregull
            int responseCode = connection.getResponseCode();

            //Nese response = 200 =OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode:" + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url_Weather.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Mbyll scanner kur te mos ket me text
                scanner.close();

                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(String.valueOf(informationString));

                //Data dataWeather=new Gson().fromJson(String.valueOf(informationString),Data.class);

                System.out.println("Moti per "+city.getCity_name()+" esht:"
                       +json.get("temp")+json.get("temp_min")+json.get("temp_max")+json.get("wind"));

                //System.out.println("Moti per "+city.getCity_name()+"esht"+json.get("temp"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

