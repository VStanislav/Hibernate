package com.voronkov.customers;

import com.voronkov.SessionFactoryUtils;
import com.voronkov.products.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDaoImp implements CustomerDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public CustomerDaoImp(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Customer findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public Customer findByName(String name) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            Customer customer = session.createQuery("select c from Customer c where c.name= :name", Customer.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Customer> findAllProducts() {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("select c from Customer c " +
                    "where c.id<10 ").getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }

    @Override
    public void saveOrUpdate(Customer customer) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateByName(Long id, String newName) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.createQuery("update Customer c set c.name=:name where c.id=:id")
                    .setParameter("name", newName)
                    .setParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(String name) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.createQuery("delete from Customer c where c.name=:name")
                    .setParameter("name",name)
                    .executeUpdate();

            session.getTransaction().commit();
        }
    }
}
