package services;

import entities.Lesson;
import entities.Student;
import inserting.AddingData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class FindNearestLesson {
    public void findingById(Integer StudentId){
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Query query = session.createQuery("select l from Lesson l join Group g on l.group.id=g.id join Student s on s.group.id = g.id where s.id = " + StudentId + " order by l.dateTime");
                query.setMaxResults(1);
                session.getTransaction().commit();
                Lesson firstLesson = (Lesson) query.getSingleResult();
                Student student = session.find(Student.class, StudentId);
            System.out.println("\nInfo about nearest lesson of student \"" + student.getName() + "\":\n" +
                    "Date and time: " + firstLesson.getDateTime() + "\n" +
                    "Teacher: " + firstLesson.getTeacher().getName() + "\n" +
                    "Theme: " + firstLesson.getTheme().getName() + "\n");
            }catch (Exception e){
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
