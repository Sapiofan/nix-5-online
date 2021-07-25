package inserting;

import entities.*;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddingData {
    public void initializing(Session session){
        Course course = new Course();
        course.setName("programming");
        session.persist(course);

        Group group = new Group();
        group.setGroupCode("6.04.121.010.1");
        course.addGroup(group);
        session.persist(course);
        session.persist(group);

        Student student = new Student();
        student.setName("Student Test");
        student.setGroup(group);
        session.persist(student);

        Teacher teacher = new Teacher();
        teacher.setName("Teacher Test");
        session.persist(teacher);

        Theme theme = new Theme();
        theme.setName("Theme");
        session.persist(theme);

        Lesson lesson = new Lesson();
        lesson.setGroup(group);
        lesson.setTeacher(teacher);
        lesson.setTheme(theme);
        Calendar calendar = new GregorianCalendar(2021, Calendar.MARCH, 26, 14, 50);
        lesson.setDateTime(calendar.getTime());
        session.persist(lesson);

        Lesson lesson1 = new Lesson();
        lesson1.setGroup(group);
        lesson1.setTeacher(teacher);
        lesson1.setTheme(theme);
        calendar = new GregorianCalendar(2021, Calendar.MARCH, 25, 14, 50);
        lesson1.setDateTime(calendar.getTime());
        session.persist(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setGroup(group);
        lesson2.setTeacher(teacher);
        lesson2.setTheme(theme);
        calendar = new GregorianCalendar(2021, Calendar.MARCH, 25, 16, 30);
        lesson2.setDateTime(calendar.getTime());
        session.persist(lesson2);

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setLesson(lesson);
        grade.setGrade((byte)9);
        session.persist(grade);
    }
}
