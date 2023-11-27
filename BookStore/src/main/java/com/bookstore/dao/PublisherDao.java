package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Publisher;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PublisherDao {

    public static List<Publisher> getAllPublishers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "FROM Publisher";
            Query<Publisher> query = session.createQuery(hql, Publisher.class);
            List<Publisher> publishers = query.list();
            session.getTransaction().commit();
            return publishers;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately (log, throw, etc.)
            throw new RuntimeException("Error while fetching publishers from the database", e);
        }
    }
}
