package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@Getter
public class HibernateUtil {

    SessionFactory sessionFactory=new Configuration()
            .configure("hibernate.cfg.xml").buildSessionFactory();

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
