package services;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class AddingOfOperations {

    private static final Logger logInfo = LoggerFactory.getLogger("info");
    private static final Logger logWarn = LoggerFactory.getLogger("warn");
    private static final Logger logError = LoggerFactory.getLogger("error");

    public void addOperation(String name, String email, String password) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Query query = session.createQuery("select u from User u where name = :name and email = :email and password = :password");
                query.setParameter("name", name);
                query.setParameter("email", email);
                query.setParameter("password", password);
                query.setMaxResults(1);
                User user = (User) query.getSingleResult();
                if (user == null) {
                    logError.error("System couldn't sign in. Wrong input. Email: " + email);
                    throw new RuntimeException("System couldn't sign in. Input was wrong");
                } else {
                    System.out.println("System signed in");
                    logInfo.info("System signed in. Email: " + email);
                }

                Scanner sc = new Scanner(System.in);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Operation operation = new Operation();
                System.out.println("Choose an invoice (input index):");
                List<Invoice> invoices = user.getInvoices();
                for (Invoice invoice : invoices) {
                    System.out.println("id:" + invoice.getId() + ", balance:" + invoice.getAmount());
                }
                int index = sc.nextInt();
                logInfo.info("index of invoice: " + index + ", size of list: " + invoices.size());
                Invoice invoice = invoices.get(index - 1);
                System.out.println("Choose the category of operation:\n" +
                        "1 >> income\n" +
                        "2 >> expense");
                String category = sc.next();
                switch (category) {
                    case "1" -> {
                        System.out.println("Input the amount that will be added:");
                        Long amount = sc.nextLong();
                        logInfo.info("inputted amount: " + amount);
                        System.out.println("Give a description of income:");
                        String description = reader.readLine();
                        logInfo.info("description: " + description);

                        Income income = new Income();
                        income.setDescription(description);
                        income.setCategories(Category.Categories.INCOME);
                        session.persist(income);

                        operation.setDifference(amount);
                        operation.setTime(Instant.now());
                        operation.setCategory(income);
                        operation.setInvoice(invoice);
                        session.persist(operation);
                    }
                    case "2" -> {
                        System.out.println("Input the amount that will be subtracted:");
                        Long amount = sc.nextLong();
                        logInfo.info("inputted amount: " + amount);
                        System.out.println("Give a description of expense:");
                        String description = reader.readLine();
                        logInfo.info("description: " + description);

                        Expense expense = new Expense();
                        expense.setDescription(description);
                        expense.setCategories(Category.Categories.EXPENSE);
                        session.persist(expense);

                        operation.setDifference(amount);
                        operation.setTime(Instant.now());
                        operation.setCategory(expense);
                        operation.setInvoice(invoice);
                        session.persist(operation);
                    }
                    default -> {
                        logWarn.warn("Incorrect number when chose action");
                        System.out.println("Wrong input");
                    }
                }
                session.getTransaction().commit();
                System.out.println("Operation was added. Current amount of invoice: " + invoice.getAmount());
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
