package com.bookstore.dao;
import java.util.List;
import com.bookstore.modal.User;
import com.bookstore.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
public class UserDao {
    public static void insertUser(User user) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public User findById(int id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        User user = session.load(User.class, id);
        return user;
    }

    public static User findByEmail(String email) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        User user = new User();
        List<User> users = null;

        try {
            final String sqlString = "Select u from User u where u.email = :email";
            Query query = session.createQuery(sqlString);
            query.setParameter("email", email);
            users = query.list();
            if (!users.isEmpty()) {
                user = users.get(0);
                System.out.println("user is" + user.getEmail());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public static List<User> getAll() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<User> users = null;
        try {
            final String sqlString = "select u from User u";
            Query query = session.createQuery(sqlString);
            users = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public static List<User> searchByName(String name) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<User> users = null;
        try {
            final String sqlString = "select u from User u where u.username like :name";
            Query query = session.createQuery(sqlString);
            query.setParameter("name", "%" + name + "%");
            users = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public static boolean isExistEmail(String email){
        boolean result = false;

        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            final String sqlString = "Select COUNT(email) AS num From User u WHERE u.email = :email";
            Query query = session.createNativeQuery(sqlString).addScalar("num", StandardBasicTypes.INTEGER);
            query.setParameter("email", email);
            int num = (int) query.uniqueResult();
            System.out.println("Số email: " + num);
            if(num == 0){
                result = true;
            }
        }
        catch(RuntimeException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
    public static User Login(String username, String password) {
        User user = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Using HQL (Hibernate Query Language) to query the User entity
            String hql = "FROM User WHERE username = :username AND password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            // Execute the query and retrieve the user
            user = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void updateCodeByEmail(String email, String otp) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        User user = findByEmail(email);

        try {
            if (user != null) {
                session.beginTransaction();
                user.setCode(otp);
                session.update(user);
                session.getTransaction().commit();
            }
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static boolean checkCodeByEmail(String email, String otp) {
        User user = findByEmail(email);
        return user != null && otp.equals(user.getCode());
    }
}
