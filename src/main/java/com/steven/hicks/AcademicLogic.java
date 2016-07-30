package com.steven.hicks;

import com.steven.hicks.entities.AcademicCourse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Steven on 7/29/2016.
 */
public class AcademicLogic
{
    public static void addCourse(AcademicCourse course)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(course);
        session.getTransaction().commit();
        session.close();
        factory.close();

    }
}
