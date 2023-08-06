package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CityRepository {

     void addCity(City city) {
    //initialize i entities
    //initialise i session
    //initialise i services (psh )

        SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
    Transaction transaction = null;

        try(
    Session session = sessionFactory.openSession())

    {
        transaction = session.beginTransaction();

        session.persist(city);
        transaction.commit();
    } catch(
    Exception e)

    {
        if (transaction != null) {
            transaction.rollback();
        }
    }


}

}
