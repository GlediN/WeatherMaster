package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int city_id;

    @Column(unique = true)
    private String city_name;



    private String country_name;

    private int default_city;
    @OneToMany(mappedBy = "city_id")
    private List<Weather> weathers;

    public City(String city_name) {
        this.city_name = city_name;
    }



}
