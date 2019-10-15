package example.util;

import example.entity.Employee;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class HibernateUtility {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            Employee employee = new Employee();
            employee.setName("Nguyễn Thành Đạt");
            employee.setSalary("1000000");
            session.save(employee);
            System.out.println("Success!!!!");
            transaction.commit();
        }
        catch (Exception ex){
            if(transaction!=null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}