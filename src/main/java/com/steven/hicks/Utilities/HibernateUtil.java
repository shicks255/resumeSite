package com.steven.hicks.Utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    public static SessionFactory sessionFactory;

    public static void initializeSessionFactory()
    {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory();
    }

    public static void destroySessionFactory()
    {
        sessionFactory.close();
    }

//    public static SessionFactory getSessionFactory()
//    {
//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties());
//
//        SessionFactory factory = configuration.buildSessionFactory();
//        return factory;
//    }

    public static void createItem(Object o)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);

        session.getTransaction().commit();
        session.close();
    }

    public static void deleteItem(Object o)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);

        session.getTransaction().commit();
        session.close();
    }

    public static void updateItem(Object o)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);

        session.getTransaction().commit();
        session.close();
    }

    public static void refreshItem(Object o)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.refresh(o);

        session.getTransaction().commit();
        session.close();
    }

    public static void mergeItem(Object o)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(o);

        session.getTransaction().commit();
        session.close();
    }

}
