package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Discount;
import com.bookstore.modal.Payment;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDao {
    public static List<Payment> getAllPayments() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Payment> payments = null;
        try {
            session.beginTransaction();
            String hql = "FROM Payment";
            Query<Payment> query = session.createQuery(hql, Payment.class);
            payments = query.list();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return payments;
    }
    public static Payment getPaymentByPaymentID(int paymentID) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Payment payment = null;
        try {
            session.beginTransaction();
            payment = session.get(Payment.class, paymentID);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return payment;
    }
}
