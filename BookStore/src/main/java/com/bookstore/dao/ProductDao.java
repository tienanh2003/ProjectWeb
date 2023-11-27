package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Category;
import com.bookstore.modal.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public static void addProduct(Product product) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public static void updateProduct(Product product) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public static void deleteProduct(int id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public static List<Product> getAllProduct() {
        // open session
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            // Create query
            final String sqlString = "select ct from Product ct";
            Query query = session.createQuery(sqlString);
            products = query.list();

            if (products == null) {
                products = new ArrayList<>();
            }
        } catch (HibernateException e) {
            e.printStackTrace(); // Replace with proper logging
        } finally {
            session.close();
        }
        return products;
    }
    public static List<Product> getProductByCategoryID(int cateID) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            session.beginTransaction();

            String hql = "SELECT p FROM Product p " +
                    "JOIN p.publisher pu " +
                    "JOIN p.productCategories pc " +
                    "JOIN pc.category c " +
                    "WHERE c.categoryID = :cateID";

            Query<Product> query = session.createQuery(hql, Product.class);
            query.setParameter("cateID", cateID);

            products = query.list();

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    public static Product getProductByID(int productID) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Product product = null;
        try {
            // Create query
            final String sqlString = "SELECT p FROM Product p WHERE p.productID = :productID";

            Query<Product> query = session.createQuery(sqlString, Product.class);
            query.setParameter("productID", productID);
            List<Product> products = query.list();
            if (!products.isEmpty()) {
                product = products.get(0);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }

    public static List<Product> searchProductByName(String name) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            // Create query
            final String sqlString = "SELECT p FROM Product p WHERE p.productName LIKE :name ";

            Query<Product> query = session.createQuery(sqlString, Product.class);
            query.setParameter("name", "%" + name + "%");
            products = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }
    public static Product getProductDetails(int productId) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Product product = null;
        try {
            session.beginTransaction();
            product = session.get(Product.class, productId);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }
}
