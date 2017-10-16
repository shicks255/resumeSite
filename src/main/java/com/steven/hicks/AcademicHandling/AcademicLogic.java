package com.steven.hicks.AcademicHandling;

import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.AcademicCourse;
import com.steven.hicks.entities.Coursework;
import com.steven.hicks.entities.FileRequest;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

        course.setSemesterTrackingNumber(AcademicLogic.getSemesterNumberForSemester(semester));

        HibernateUtil.createItem(course);
    }

    public static List<AcademicCourse> getCourseList()
    {
        Session session = HibernateUtil.sessionFactory.openSession();

        Query query = session.createQuery("from AcademicCourse order by semesterTrackingNumber");
        List<AcademicCourse> list = query.list();
        session.close();

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
        coursework.setAcademicCourse(course);

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
        course.getCourseworks().add(coursework);

        HibernateUtil.createItem(coursework);
        HibernateUtil.updateItem(course);

        File tempFile = file.getAbsoluteFile();
        tempFile.delete();
        file.delete();

        return errorMessage.toString();
    }

    public static String deleteCoursework(String fileName)
    {
        Coursework coursework = Coursework.getCourseworkByFileName(fileName);

        String errorMessage = "";
        if (coursework != null)
            HibernateUtil.deleteItem(coursework);

        if (coursework == null)
            errorMessage = "Error...coursework not found.";

        return errorMessage;
    }

    public static void printCoursework(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String courseworkName = request.getParameter("courseworkName");
        Coursework coursework = Coursework.getCourseworkByFileName(courseworkName);

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

    public static BigDecimal getIntFromLetterGrade(String letter)
    {
        switch (letter)
        {
            case "A" : return new BigDecimal("4");

            case "A-" : return new BigDecimal("3.5");

            case "B" : return new BigDecimal("3");

            case "B-": return new BigDecimal("2.5");

            case "C": return new BigDecimal("2");

            case "C-": return new BigDecimal("1.5");

            case "D": return new BigDecimal("1");

            case "D-": return new BigDecimal("0.5");

            default : return BigDecimal.ZERO;
        }
    }

    public static String getLetterFromBigDecimal(BigDecimal grade)
    {
//        3.5 - 4.0 = A
//        3.00 - 3.49 = A-
//        2.5 - 2.99 = B
//        2.0 - 2.49 = B-
//        1.50 - 1.99 = C
//        1.0 - 1.49 = C-;
//        .5 - .99 = C
//        0.0 - .49 = D


        String letter = "";

        if (grade.compareTo(new BigDecimal("3.5")) >= 0)
            letter = "A";
        if (grade.compareTo(new BigDecimal("3.0")) >=0 && grade.compareTo(new BigDecimal("3.49")) <= 0)
            letter = "A-";
        if (grade.compareTo(new BigDecimal("2.5")) >= 0 && grade.compareTo(new BigDecimal("2.99")) <= 0)
            letter = "B";
        if (grade.compareTo(new BigDecimal("2.0")) >= 0 && grade.compareTo(new BigDecimal("2.49")) <= 0)
            letter = "B-";
        if (grade.compareTo(new BigDecimal("1.5")) >= 0 && grade.compareTo(new BigDecimal("1.99")) <= 0)
            letter = "B";
        if (grade.compareTo(new BigDecimal("1.0")) >= 0 && grade.compareTo(new BigDecimal("1.49")) <= 0)
            letter = "C";
        if (grade.compareTo(new BigDecimal(".5")) >= 0 && grade.compareTo(new BigDecimal(".99")) <= 0)
            letter = "C-";
        if (grade.compareTo(new BigDecimal("0")) >= 0 && grade.compareTo(new BigDecimal("0.49")) <= 0)
            letter = "D";

        return letter;
    }
}
