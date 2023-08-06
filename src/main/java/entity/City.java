package entity;

import jakarta.persistence.*;

@Entity
public class City {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE )
private  int city_id;
private String city_name;
private String country_name;

private int default_city;

@OneToMany(mappedBy = "city_id")
   private Weather weather;



}
