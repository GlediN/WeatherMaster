import jakarta.persistence.*;

@Entity(name = "Qyteti")
public class Qyteti {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE )
private  int qyteti_id;
@OneToMany(mappedBy = "qyteti_id")
   private Moti moti;



}
