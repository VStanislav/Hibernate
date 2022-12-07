package com.voronkov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateApp {
    private SessionFactoryUtils sessionFactoryUtils;

    public HibernateApp (SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.voronkov");
        Servise servise = context.getBean(Servise.class);

        //сервис поиска продуктов по id пользователя
        servise.findProductsByCustomer(1L);

        //сервис поиска пользователей по id продукта
        servise.findCustomersByProduct(2L);


    }
}
