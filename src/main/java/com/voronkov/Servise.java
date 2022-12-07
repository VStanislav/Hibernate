package com.voronkov;


import com.voronkov.customers.CustomerDao;
import com.voronkov.products.ProductDao;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Servise {
    ProductDao productDao;
    CustomerDao customerDao;
    SessionFactoryUtils sessionFactoryUtils;

    public Servise(ProductDao productDao, CustomerDao customerDao, SessionFactoryUtils sessionFactoryUtils) {
        this.productDao = productDao;
        this.customerDao = customerDao;
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    public void findProductsByCustomer(Long id) {

        try (Session session= sessionFactoryUtils.getSession()){
            session.beginTransaction();
            List<Order> orderList = session.createQuery("select o from Order o where o.customer.id= :customer_id",Order.class)
                    .setParameter("customer_id",id)
                    .getResultList();

            System.out.println("Список продуктов пользователя - " );
            System.out.println(orderList);
        }
    }

    public void findCustomersByProduct(Long id) {
        try (Session session= sessionFactoryUtils.getSession()){
            session.beginTransaction();
            List<Order> orderList = session.createQuery("select o from Order o where o.product.id= :product_id",Order.class)
                    .setParameter("product_id",id)
                    .getResultList();
            System.out.println("Список пользователей купивших продукт - ");
            System.out.println(orderList);
        }
    }
}
