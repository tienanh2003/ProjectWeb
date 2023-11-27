package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Review;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    public static List<Object[]> getReviewAndUsernameByProductID(int productID) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Object[]> results = new ArrayList<>();

        try {
            final String sqlString = "SELECT r.reviewID, r.reviewDate, r.reviewStar, r.reviewDescription, u.username " +
                    "FROM Review r JOIN r.userID u JOIN r.productID p " +
                    "WHERE p.productID = :productID";
            Query<Object[]> query = session.createQuery(sqlString);
            query.setParameter("productID", productID);

            results = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }


    public static void addReview(Review review) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(review);
            transaction.commit();
            System.out.println("Review added successfully!");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error adding review: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public static void updateReview(Review review) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(review);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
