package initializing;

import entities.*;
import org.hibernate.Session;

public class FillInTables {
    public void fillIn(Session session) {
        User user = new User();
        user.setName("Test Name");
        user.setEmail("testname@gmail.com");
        user.setPassword("password");
        session.persist(user);

        Invoice invoice = new Invoice();
        invoice.setUser(user);
        session.persist(invoice);

        Income income = new Income();
        income.setDescription("salary");
        income.setCategories(Category.Categories.INCOME);
        session.persist(income);

        Operation operation = new Operation();
        operation.setDifference(100l);
        operation.setCategory(income);
        operation.setInvoice(invoice);
        session.persist(operation);

        Expense expense = new Expense();
        expense.setDescription("buy products in market");
        expense.setCategories(Category.Categories.EXPENSE);
        session.persist(expense);

        operation = new Operation();
        operation.setDifference(10l);
        operation.setCategory(expense);
        operation.setInvoice(invoice);
        session.persist(operation);

    }
}
