package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Visitor
{
    @Id
    private String ipAddress = "";

    @Column
    private int countOfVisits;

    public Visitor()
    {}

    @Override
    public String toString()
    {
        return "Visitor{" +
                "ipAddress='" + ipAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        return ipAddress.equals(visitor.ipAddress);
    }

    @Override
    public int hashCode()
    {
        return ipAddress.hashCode();
    }

    public static Visitor getVisitor(String ip)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Visitor visitor = session.get(Visitor.class, ip);

        session.close();
        sessionFactory.close();

        return visitor;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public int getCountOfVisits()
    {
        return countOfVisits;
    }

    public void setCountOfVisits(int countOfVisits)
    {
        this.countOfVisits = countOfVisits;
    }
}
