package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Orders;
import com.bookstore.modal.OrderItem;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDao {

    public void addOrder(Orders order) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addOrderItem(OrderItem orderItem) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(orderItem);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Orders updatedOrder) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(updatedOrder);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Orders> getAllOrders() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Orders", Orders.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Orders getOrderByOrderID(int orderID) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.find(Orders.class, orderID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderItem> getAllOrderItemsByOrderID(int orderID) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Orders order = session.find(Orders.class, orderID);
            if (order != null) {
                return order.getOrderItems();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Orders> getOrdersByUserID(int userID) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createNativeQuery("SELECT * FROM Orders WHERE userID = :userID", Orders.class)
                    .setParameter("userID", userID)
                    .list();
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return null;
    }
}
