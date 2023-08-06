package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        City city1 = new City("Tirane");
        City city2 = new City("Shkoder");

      SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
        Session session= sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

session.persist(city1);
session.persist(city2);
transaction.commit();



    }
}
