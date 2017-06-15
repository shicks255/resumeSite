package com.steven.hicks.Utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUtil
{
    public static SessionFactory getSessionFactory()
    {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory factory = configuration.buildSessionFactory();
        return factory;
    }

    public static void createItem(Object o)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(o);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public static void deleteItem(Object o)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(o);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public static void updateItem(Object o)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(o);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public static void refreshItem(Object o)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.refresh(o);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public static void mergeItem(Object o)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.merge(o);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

}
