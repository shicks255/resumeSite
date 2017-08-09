package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;

@Entity
@Table
public class Coursework
{
    @Id
    private String fileName = "";

    @Column
    private String year = "";
    @Column
    private String course = "";
    @Column
    private String semester = "";
    @Column
    private String additionalNotes = "";
    @Column
    private String grade = "";

    @ManyToOne
    @JoinColumn(name = "course_fk")
    private AcademicCourse academicCourse;

    @Column
    @Lob
    private byte[] file;

    @Override
    public String toString()
    {
        return "Coursework{" +
                "fileName='" + fileName + '\'' +
                ", year='" + year + '\'' +
                ", course='" + course + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coursework that = (Coursework) o;

        return fileName.equals(that.fileName);
    }

    public static Coursework getCourseworkByFileName(String fileName)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Coursework coursework = session.get(Coursework.class, fileName);
        session.close();
        factory.close();

        return coursework;
    }

    @Override
    public int hashCode()
    {
        return fileName.hashCode();
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getCourse()
    {
        return course;
    }

    public void setCourse(String course)
    {
        this.course = course;
    }

    public String getSemester()
    {
        return semester;
    }

    public void setSemester(String semester)
    {
        this.semester = semester;
    }

    public String getAdditionalNotes()
    {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes)
    {
        this.additionalNotes = additionalNotes;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public byte[] getFile()
    {
        return file;
    }

    public void setFile(byte[] file)
    {
        this.file = file;
    }

    public AcademicCourse getAcademicCourse()
    {
        return academicCourse;
    }

    public void setAcademicCourse(AcademicCourse academicCourse)
    {
        this.academicCourse = academicCourse;
    }
}
