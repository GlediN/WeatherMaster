package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int weather_id;

    private double temp;
    private LocalDate date_recived_info;
    private double min_temp;
    private double max_temp;
    private double humidity_level;
    private double wind_speed;
    private double atmosferic_pressure;
    private LocalDate date_for_info;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city_id;


}
