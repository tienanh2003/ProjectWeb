package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Discount;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DiscountDao {
    public static List<Discount> getAllDiscounts() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Discount> discounts = null;
        try {
            session.beginTransaction();
            String hql = "FROM Discount";
            Query<Discount> query = session.createQuery(hql, Discount.class);
            discounts = query.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return discounts;
    }
    public static long getPriceByDiscountCode(String discountCode) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        long price = 0;
        try {
            session.beginTransaction();

            String hql = "SELECT d.discountPrice FROM Discount d WHERE d.discountCode = :discountCode";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("discountCode", discountCode);

            Long result = query.uniqueResult();
            if (result != null) {
                price = result;  // Không cần chuyển đổi từ Long sang int
                System.out.println(price);
            }

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return price;
    }

}
