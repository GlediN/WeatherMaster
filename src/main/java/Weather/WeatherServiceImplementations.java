package Weather;

import City.City;
import Weather.WeatherServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class WeatherServiceImplementations implements WeatherServiceInterface {

    @Override
    public void default_cities() {
        List<City>defaultCityList=new ArrayList<>();
        defaultCityList.add(new City("Tirana"));
        defaultCityList.add(new City("Durres"));
        defaultCityList.add(new City("Elbasan"));
    }

    @Override
    public void weather_by_city() {

    }
}
