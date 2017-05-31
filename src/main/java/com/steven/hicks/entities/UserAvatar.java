package com.steven.hicks.entities;

import com.steven.hicks.Utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;

@Entity
public class UserAvatar
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int objectId;

    @Column
    @Lob
    private byte[] picture;

    @OneToOne(mappedBy = "avatar")
    private User user;

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
        if (avatarObjectId != null)
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            UserAvatar avatar = session.get(UserAvatar.class, avatarObjectId);

            session.close();
            sessionFactory.close();

            return avatar;
        }
        return null;
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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
