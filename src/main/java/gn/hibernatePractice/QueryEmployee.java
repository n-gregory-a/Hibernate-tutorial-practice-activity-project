package gn.hibernatePractice;

import gn.hibernatePractice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@SuppressWarnings("ALL")
public class QueryEmployee {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        System.out.println("Creating session...");
        Session session = factory.getCurrentSession();
        System.out.println("Session created.");

        try {

            // start a transaction
            System.out.println("Starting transaction....");
            session.beginTransaction();
            System.out.println("Transaction started.");

            // query employee
            System.out.println("Querying...");
            List<Employee> employees = session.createQuery("from Employee e where e.company='Google'").getResultList();
            System.out.println("Querying succes...");

            // display result
            for (Employee tempEmployee: employees) {
                System.out.println(tempEmployee);
            }

            // commit transaction
            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}
