package entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        City city = new City("Tirana");
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

                Data dataWeather=new Gson().fromJson(String.valueOf(informationString),Data.class);

                System.out.println("Moti per "+city.getCity_name()+" esht:"+informationString);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
@Getter
@Setter
class  Data_Weather{
private String name;
private double temp;
private double temp_min;
private double temp_max;
private double wind;

    @Override
    public String toString() {
        return String.format("name:%s,temp:%.2f,temp_min:%.2f,temp_max:%.2f,wind:%.2f",
                name,temp,temp_min,temp_max,wind);
    }
}

