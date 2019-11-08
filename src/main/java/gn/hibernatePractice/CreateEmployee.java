package gn.hibernatePractice;

import gn.hibernatePractice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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
            Employee employee = new Employee("Sonya", "Blade", "Boing");
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

            // QUERY EMPLOYEE OBJECT FOR A GIVEN COMPANY

            // start new session and transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // query employee
            List<Employee> employees = session.createQuery("from employee").getResultList();

            //display employee
            System.out.println("Employee of Google");
            for (Employee tempEmployee1: employees) {
                System.out.println(tempEmployee1);
            }

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
