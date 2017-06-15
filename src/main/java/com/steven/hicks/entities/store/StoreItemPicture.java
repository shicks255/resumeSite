package com.steven.hicks.entities.store;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItemGeneric;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class StoreItemPicture
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int objectId;

    @Column
    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "itemNumber")
    private StoreItemGeneric storeItemGeneric;

    @Column
    private String pictureCaption = "";

    @Override
    public String toString()
    {
        return "StoreItemPicture{" +
                "objectId=" + objectId +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreItemPicture that = (StoreItemPicture) o;

        return objectId == that.objectId;
    }

    @Override
    public int hashCode()
    {
        return objectId;
    }

    public static StoreItemPicture getItemPicture(int objectId)
    {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        StoreItemPicture picture = session.get(StoreItemPicture.class, objectId);

        session.close();
        factory.close();

        return picture;
    }


    public int getObjectId()
    {
        return objectId;
    }

    public void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }

    public String getPictureCaption()
    {
        return pictureCaption;
    }

    public void setPictureCaption(String pictureCaption)
    {
        this.pictureCaption = pictureCaption;
    }

    public StoreItemGeneric getStoreItemGeneric()
    {
        return storeItemGeneric;
    }

    public void setStoreItemGeneric(StoreItemGeneric storeItemGeneric)
    {
        this.storeItemGeneric = storeItemGeneric;
    }
}
