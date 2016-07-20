package com.steven.hicks.entities;

/**
 * Created by Steven on 7/18/2016.
 */
public class AcademicCourse
{
    private String courseName = "";
    private String courseCode = "";
    private String college = "";
    private String semester = "";
    private String gradeReceived = "";

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

        if (!courseCode.equals(that.courseCode)) return false;
        return semester.equals(that.semester);

    }

    @Override
    public int hashCode()
    {
        int result = courseCode.hashCode();
        result = 31 * result + semester.hashCode();
        return result;
    }

//    ----------Getters & Setters


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

    public String getGradeReceived()
    {
        return gradeReceived;
    }

    public void setGradeReceived(String gradeReceived)
    {
        this.gradeReceived = gradeReceived;
    }
}
