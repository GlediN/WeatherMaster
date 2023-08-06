package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
        CityRepository cityRepository = new CityRepository();
        cityRepository.addCity(new City("Tirane"));


    }

}
