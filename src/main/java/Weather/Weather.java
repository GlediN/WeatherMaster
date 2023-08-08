package Weather;

import City.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int weather_id;
    private LocalDate date_received_info;
    private Double temp;
    private Double temp_min;
    private Double temp_max;
    private Double humidity_level;
    private Double wind_speed;
    private Double atmospheric_pressure;
    private LocalDate date_for_info;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city_id;

}
