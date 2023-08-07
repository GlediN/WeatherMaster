package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;


@Getter
@Setter
public class ApiResponse {

    private double temp;
    private double temp_min;
    private double temp_max;
    private double humidity;
    private double speed;
    private double pressure;
    private long dt;

    City city = new City("Tirana");

 String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +city.getCity_name()
        +"&units=metric&appid=30d43c790a5ff92c061bdaf9c866a004";





}
