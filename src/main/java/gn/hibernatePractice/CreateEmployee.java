package gn.hibernatePractice;

import gn.hibernatePractice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateEmployee {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            // create a employee object
            System.out.println("Creating new employee object...");
            Employee employee = new Employee("Henry", "Ford", "Boing");
            System.out.println(employee);

            // start a transaction
            session.beginTransaction();

            // save the employee
            System.out.println("Saving student...");
            session.save(employee);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

            // RETRIEVE OBJECT BY PRIMARY KEY
            int key = 8;

            // start new session and transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student by primary key
            Employee retrievedEmployee = session.get(Employee.class, key);

            System.out.println("Retrieved employee by key " + key + ": " + retrievedEmployee);

            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
