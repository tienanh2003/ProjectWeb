package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDao {

    public static List<Category> getAllCategories() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Category> categories = null;
        try {
            session.beginTransaction();
            String hql = "FROM Category";
            Query<Category> query = session.createQuery(hql, Category.class);
            categories = query.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }
    public Category getCategoryById(int categoryID) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Category category = null;
        try {
            session.beginTransaction();

            // Assuming "Category" is the name of your entity and "categoryID" is the primary key
            category = session.get(Category.class, categoryID);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return category;
    }
}
