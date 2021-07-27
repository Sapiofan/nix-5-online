package main;

import inserting.AddingData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FillInTables {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            AddingData addingData = new AddingData();
            try {
                session.getTransaction().begin();
                addingData.initializing(session);
                session.getTransaction().commit();
            }
            catch (Exception e){
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
