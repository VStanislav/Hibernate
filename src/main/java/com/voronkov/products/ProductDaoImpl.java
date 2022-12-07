package com.voronkov.products;

import com.voronkov.SessionFactoryUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Product findByName(String name) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            Product product = session.createQuery("select p from Product p where p.name= :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Product findByPrice(Integer price) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Product product = null;
            try {
                 product = session.createQuery("select p from Product p where p.price= :price", Product.class)
                        .setParameter("price", price)
                        .getSingleResult();
            }catch (Exception e){
                System.out.println("Not find product via price");
            }
            return product;
        }
    }

    @Override
    public List<Product> findAllProducts() {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p " +
                    "where p.id<10 ").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public void saveOrUpdate(Product product) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateByName(Long id, String newName) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setName(newName);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(String name) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
//            session.delete(session.get(Product.class,id));

            session.createQuery("delete from Product p where p.name=:name")
                            .setParameter("name",name)
                                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
