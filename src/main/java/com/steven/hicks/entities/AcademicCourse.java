package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = { @Index(name = "SEMESTER_TRACKING_NUMBER_INDX", columnList = "semesterTrackingNumber") })
public class AcademicCourse
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int objectId = 0;

    @Column
    private String courseCode = "";
    @Column
    private String courseName = "";
    @Column
    private String college = "";
    @Column
    private String semester = "";
    @Column
    private int semesterTrackingNumber;
    @Column
    private String gradeReceived = "";

    @OneToMany(mappedBy = "academicCourse", fetch = FetchType.EAGER)
    private List<Coursework> courseworks = new ArrayList<>();

    public AcademicCourse()
    {}

    @Override
    public String toString()
    {
        return "AcademicCourse{" +
                "courseName='" + courseName + '\'' +
                ", college='" + college + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcademicCourse that = (AcademicCourse) o;

        return objectId == that.objectId;
    }

    @Override
    public int hashCode()
    {
        return objectId;
    }

    public static AcademicCourse getCourse(int courseId)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        AcademicCourse course = session.get(AcademicCourse.class, courseId);
        session.close();
        factory.close();
        return course;
    }

    public int countOfCoursework()
    {
        return courseworks.size();
    }

    //    ----------Getters & Setters


    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseCode()
    {
        return courseCode;
    }

    public void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }

    public String getCollege()
    {
        return college;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }

    public String getSemester()
    {
        return semester;
    }

    public void setSemester(String semester)
    {
        this.semester = semester;
    }

    public int getSemesterTrackingNumber()
    {
        return semesterTrackingNumber;
    }

    public void setSemesterTrackingNumber(int semesterTrackingNumber)
    {
        this.semesterTrackingNumber = semesterTrackingNumber;
    }

    public String getGradeReceived()
    {
        return gradeReceived;
    }

    public void setGradeReceived(String gradeReceived)
    {
        this.gradeReceived = gradeReceived;
    }

    public List<Coursework> getCourseworks()
    {
        return courseworks;
    }

    public void setCourseworks(List<Coursework> courseworks)
    {
        this.courseworks = courseworks;
    }
}
