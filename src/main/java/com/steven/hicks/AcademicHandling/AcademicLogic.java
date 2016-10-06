package com.steven.hicks.AcademicHandling;

import com.steven.hicks.HibernateUtil;
import com.steven.hicks.entities.AcademicCourse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

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

    public static List<AcademicCourse> getCourseList()
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from AcademicCourse order by semesterTrackingNumber");
        List<AcademicCourse> list = query.list();
        session.close();
        sessionFactory.close();

        return list;
    }

    public static AcademicCourse getCourse(int courseId)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        AcademicCourse course = session.get(AcademicCourse.class, courseId);

        session.close();
        sessionFactory.close();

        return course;

    }

    public static int getSemesterNumberForSemester(String semester)
    {
        int semesterNumber = 0;

        switch(semester)
        {
            case "2007Fall": semesterNumber = 200703;
                break;

            case "2008Spring": semesterNumber = 200801;
                break;

            case "2008Summer": semesterNumber = 200802;
                break;

            case "2008Fall": semesterNumber = 200803;
                break;

            case "2009Spring": semesterNumber = 200901;
                break;

            case "2009Summer": semesterNumber = 200902;
                break;

            case "2009Fall": semesterNumber = 200903;
                break;

            case "2010Spring": semesterNumber = 201001;
                break;

            case "2010Summer": semesterNumber = 201002;
                break;

            case "2010Fall": semesterNumber = 201003;
                break;

            case "2011Spring": semesterNumber = 201101;
                break;

            case "2011Summer": semesterNumber = 201102;
                break;

            case "2011Fall": semesterNumber = 201103;
                break;

            case "2012Spring": semesterNumber = 201201;
                break;

            case "2012Summer": semesterNumber = 201202;
                break;

            case "2012Fall": semesterNumber = 201203;
                break;

            case "2013Spring": semesterNumber = 201301;
                break;

            case "2013Summer": semesterNumber = 201302;
                break;

            case "2013Fall": semesterNumber = 201303;
                break;

            case "2014Spring": semesterNumber = 201401;
                break;

            case "2014Summer": semesterNumber = 201402;
                break;

            case "2014Fall": semesterNumber = 201403;
                break;

            case "2015Spring": semesterNumber = 201501;
                break;

            case "2015Summer": semesterNumber = 201502;
                break;

            case "2015Fall": semesterNumber = 201503;
                break;

            case "2016Spring": semesterNumber = 201601;
                break;

            case "2016Summer": semesterNumber = 201602;
                break;

            case "2016Fall": semesterNumber = 201603;
                break;
        }
        return semesterNumber;
    }
}
