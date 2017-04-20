package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;

/**
 * Created by Steven on 0018, April 18, 2017.
 */

@Entity
public class UserAvatar
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int objectId;

    @Column
    @Lob
    private byte[] picture;

    @Override
    public String toString()
    {
        return "UserAvatar{" +
                "objectId=" + objectId +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAvatar that = (UserAvatar) o;

        return objectId == that.objectId;
    }

    public static UserAvatar getAvatar(Integer avatarObjectId)
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        UserAvatar avatar = session.get(UserAvatar.class, avatarObjectId);

        session.close();
        sessionFactory.close();

        return avatar;
    }

    @Override
    public int hashCode()
    {
        return objectId;
    }

    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }
}
