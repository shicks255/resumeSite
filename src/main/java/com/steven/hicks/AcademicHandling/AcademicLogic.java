package com.steven.hicks.AcademicHandling;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.entities.AcademicCourse;
import com.steven.hicks.entities.Coursework;
import com.steven.hicks.entities.FileRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AcademicLogic
{
    public static void addCourse(HttpServletRequest request)
    {
        String courseName = request.getParameter("courseName");
        String courseCode = request.getParameter("courseCode");
        String collegeName = request.getParameter("collegeName");
        String courseGrade = request.getParameter("courseGrade");
        String semester = request.getParameter("semester");

        AcademicCourse course = new AcademicCourse();
        course.setCourseName(courseName);
        course.setCourseCode(courseCode);
        course.setCollege(collegeName);
        course.setGradeReceived(courseGrade);
        course.setSemester(semester);
        course.setCountOfCourseworks(0);

        course.setSemesterTrackingNumber(AcademicLogic.getSemesterNumberForSemester(semester));

        HibernateUtil.createItem(course);
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

    public static void editCourse(HttpServletRequest request, AcademicCourse course)
    {
        course.setCourseName(request.getParameter("courseNameEdit"));
        course.setCourseCode(request.getParameter("courseCodeEdit"));
        course.setCollege(request.getParameter("collegeNameEdit"));
        course.setSemester(request.getParameter("semesterEdit"));
        course.setGradeReceived(request.getParameter("courseGradeEdit"));

        HibernateUtil.updateItem(course);
    }

    public static void deleteCourse(AcademicCourse course)
    {
        HibernateUtil.deleteItem(course);
    }

    public static String saveCoursework(File file, FileRequest fr) throws IOException
    {
        StringBuilder errorMessage = new StringBuilder("");
        if (file == null || !file.isFile())
            errorMessage.append("No valid file was selected");

        Map<String, String> params = fr.getParameters();

        AcademicCourse course = AcademicCourse.getCourse(Integer.valueOf(params.get("uploadCourseId")));

        String fileName = fr.getShortFilename().substring(fr.getShortFilename().lastIndexOf(File.separator) + 1);

        Coursework coursework = new Coursework();
        coursework.setAdditionalNotes("");
        coursework.setCourse(course.getCourseName());
        coursework.setGrade(course.getGradeReceived());
        coursework.setFileName(fileName);
        coursework.setAdditionalNotes(params.get("courseworkNotes"));

        String year = course.getSemester().substring(0,4);
        String semester = course.getSemester().substring(4);
        coursework.setYear(year);
        coursework.setSemester(semester);
        coursework.setCourseId(course.getObjectId());

        byte[] bytes = new byte[(int) file.length()];

        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(bytes);
            inputStream.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        coursework.setFile(bytes);

        HibernateUtil.createItem(coursework);
        course.setCountOfCourseworks(course.getCountOfCourseworks()+1);
        HibernateUtil.updateItem(course);

        File tempFile = file.getAbsoluteFile();
        tempFile.delete();
        file.delete();

        return errorMessage.toString();
    }

    public static String deleteCoursework(String fileName)
    {
        Coursework coursework = AcademicLogic.getCourseworkByFileName(fileName);

        String errorMessage = "";
        if (coursework != null)
        {
            HibernateUtil.deleteItem(coursework);

            AcademicCourse course = AcademicCourse.getCourse(coursework.getCourseId());
            if (course != null)
            {
                course.setCountOfCourseworks(course.getCountOfCourseworks() > 0 ? course.getCountOfCourseworks() - 1 : 0);
                HibernateUtil.updateItem(course);
            }
        }
        if (coursework == null)
            errorMessage = "Error...coursework not found.";

        return errorMessage;
    }

    public static void printCoursework(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String courseworkName = request.getParameter("courseworkName");
        Coursework coursework = AcademicLogic.getCourseworkByFileName(courseworkName);

        String fileName = coursework.getFileName();
        String prefix = fileName.substring(0, fileName.lastIndexOf("."));
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        File tempFile = File.createTempFile(prefix, suffix, new File(System.getProperty("java.io.tmpdir")));

        response.setContentType(CommonUtils.getMimeType(fileName));
        response.addHeader("Content-Disposition", "filename=\"" + coursework.getFileName() + "\"");

        try(FileOutputStream outputStream = new FileOutputStream(tempFile))
        {
            outputStream.write(coursework.getFile());
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        byte[] buffer = new byte[16_000];
        ServletOutputStream outputStream1 = response.getOutputStream();
        try(FileInputStream inputStream = new FileInputStream(tempFile))
        {
            for (int bytesRead = inputStream.read(buffer); bytesRead >= 0; bytesRead = inputStream.read(buffer))
                outputStream1.write(buffer, 0, bytesRead);
            outputStream1.flush();
            outputStream1.close();
        }

        tempFile.delete();
    }

    public static List<Coursework> getCoursework(AcademicCourse course)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Coursework  where course = :course ");
        query.setParameter("course", course.getCourseName());
        List courseWork = query.list();

        session.close();
        sessionFactory.close();

        return courseWork;
    }

    public static Coursework getCourseworkByFileName(String fileName)
    {
        return Coursework.getCourseworkByFileName(fileName);
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
