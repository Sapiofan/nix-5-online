package services;

import entities.Lesson;
import entities.Student;
import inserting.AddingData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FindNearestLesson {
    public void findingById(Integer StudentId){
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            AddingData addingData = new AddingData();
            try {
                session.getTransaction().begin();
                addingData.initializing(session);
                session.getTransaction().commit();
                Student student = session.find(Student.class, StudentId);
                List<Lesson> lessons = student.getGroup().getLessons();
                int counter = 0;
                Lesson firstLesson = null;
                for (Lesson lesson : lessons) {
                    if(counter == 0){
                        firstLesson = lesson;
                        counter++;
                        continue;
                    }
                    if(firstLesson.getDateTime().compareTo(lesson.getDateTime()) > 0) {
                        firstLesson = lesson;
                    }
                }
                System.out.println("\nInfo about nearest lesson of student \"" + student.getName() + "\":\n" +
                        "Date and time: " + firstLesson.getDateTime() + "\n" +
                        "Teacher: " + firstLesson.getTeacher().getName() + "\n" +
                        "Theme: " + firstLesson.getTheme().getName() + "\n");
            }
            catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
