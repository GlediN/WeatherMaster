package entity;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@Getter
public class HibernateUtil {

    SessionFactory sessionFactory=new Configuration()
            .configure("hibernate.cfg.xml").buildSessionFactory();


}
