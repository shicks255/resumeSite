package com.steven.hicks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
    @Column
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
}
