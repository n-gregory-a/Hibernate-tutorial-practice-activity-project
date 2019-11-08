package gn.hibernatePractice;

import gn.hibernatePractice.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryEmployee {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query employee
            List<Employee> employees = session.createQuery("from employee").getResultList();

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
