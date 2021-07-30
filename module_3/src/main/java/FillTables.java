import initializing.FillInTables;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FillTables {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            FillInTables fill = new FillInTables();
            try {
                session.getTransaction().begin();
                fill.fillIn(session);
                session.getTransaction().commit();
            }
            catch (Exception e){
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
